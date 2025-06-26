package com.microservice.pedido.service.impl;

import com.microservice.commons.enums.OrderStatus;
import com.microservice.pedido.broker.OrderOutput;
import com.microservice.pedido.model.Item;
import com.microservice.pedido.model.Order;
import com.microservice.pedido.model.Payment;
import com.microservice.pedido.repository.OderRepository;
import com.microservice.pedido.repository.ProductRepository;
import com.microservice.pedido.service.OderService;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Orderserviceimpl implements OderService {

    private final OderRepository oderRepository;
    private final ProductRepository productRepository;
    private final OrderOutput orderOutput;

    @Override
    public Order create(Order order) {
        List<Item> items = getItems(order);
        order.setItems(items);
        order.calcularValorTotal();
        oderRepository.save(order);
        orderOutput.pedidoCriado(order);
        log.info("[{}] Pedido Criado", order.getId());
        return order;
    }

    @Override
    public Order findById(String id) {
        return oderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nenhum pedido encontrado ‘%s’"+ id));
    }

    @Override
    public void processarReservaDePedido(String orderId) {
        oderRepository.reserve(orderId);
       Order order = oderRepository.findById(orderId).orElseThrow(() ->
               new RuntimeException("Order not found"));
        log.info("[{}] Pedido Reservado", orderId);
        if (order.isQualified())
            confirmar(orderId);
    }

    @Override
    public void processarQualificacaoDePedido(String orderId) {
        int order = oderRepository.qualify(orderId);
        Order order1 = oderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found"));
        log.info("[{}] Pedido Reservado", orderId);
        if (order1.isQualified())
            confirmar(orderId);
    }


    @Override
    public void processarRecusaDePedido(String orderId) {
        LocalDateTime updateAt = LocalDateTime.now();
        oderRepository.updateByStatus(OrderStatus.cancelled, orderId, updateAt);
        log.info("[{}] Pedido Recusado", orderId);
    }

    @Override
    public void processarPagamentoAutorizado(String orderId, Payment payment) {
        LocalDateTime updateAt = LocalDateTime.now();
        oderRepository.updatePagameto(orderId, payment);
        oderRepository.updateByStatus(OrderStatus.in_preparation, orderId, updateAt);

        log.info("[{}] Pagamento Autorizado :(", orderId);
    }

    @Override
    public void processarPagamentoNaoAutorizado(String orderId, Payment payment) {
        LocalDateTime updateAt = LocalDateTime.now();
        oderRepository.updatePagameto(orderId, payment);
        oderRepository.updateByStatus(OrderStatus.cancelled, orderId, updateAt);

        log.info("[{}] Pagamento Não Autorizado :(", orderId);
    }

    @Override
    public void processarEnvioDePedido(String orderId) {
        LocalDateTime updateAt = LocalDateTime.now();
        oderRepository.updateByStatus(OrderStatus.sent, orderId, updateAt);
        log.info("[{}] Pedido Enviado", orderId);
    }

    @Override
    public void processarEntregaDePedido(String orderId) {
        LocalDateTime updateAt = LocalDateTime.now();
        oderRepository.updateByStatus(OrderStatus.delivered, orderId, updateAt);
        log.info("[{}] Pedido Entregue :)", orderId);
    }

    private void confirmar(String orderId) {
        LocalDateTime updateAt = LocalDateTime.now();
        oderRepository.updateByStatus(OrderStatus.confirmed, orderId, updateAt);
        Order order =  oderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found"));
        orderOutput.pedidoConfirmado(order);
        log.info("[{}] Pedido Confirmado", orderId);
    }

    private @NotNull List<Item> getItems(Order order) {
        List<Item> items = new ArrayList<>();
        for (Item item : order.getItems()) {
            var product = productRepository.findById(item.getProductId());
            item.setProductId(product.get().getId());
            item.setCount(order.getItems().get(0).getCount());
            item.setPrice(order.getItems().get(0).getPrice());
            items.add(item);
        }
        return items;
    }
}
