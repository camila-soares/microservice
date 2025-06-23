package com.microservice.pedido.service.impl;

import com.microservice.pedido.broker.OrderOutput;
import com.microservice.pedido.dto.Item;
import com.microservice.pedido.dto.enums.OrderStatus;
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
        Order order = oderRepository.reserve(orderId);
        log.info("[{}] Pedido Reservado", orderId);
        if (order.isQualified())
            confirmar(orderId);
    }

    @Override
    public void processarQualificacaoDePedido(String orderId) {
        Order order = oderRepository.qualify(orderId);

        log.info("[{}] Pedido Reservado", orderId);
        if (order.isQualified())
            confirmar(orderId);
    }


    @Override
    public void processarRecusaDePedido(String orderId) {
        oderRepository.updateByStatus(OrderStatus.cancelled, orderId);
        log.info("[{}] Pedido Recusado", orderId);
    }

    @Override
    public void processarPagamentoAutorizado(String orderId, Payment payment) {
        oderRepository.updatePagameto(orderId, payment);
        oderRepository.updateByStatus(OrderStatus.in_preparation, orderId);

        log.info("[{}] Pagamento Autorizado :(", orderId);
    }

    @Override
    public void processarPagamentoNaoAutorizado(String orderId, Payment payment) {
        oderRepository.updatePagameto(orderId, payment);
        oderRepository.updateByStatus(OrderStatus.cancelled, orderId);

        log.info("[{}] Pagamento Não Autorizado :(", orderId);
    }

    @Override
    public void processarEnvioDePedido(String orderId) {
        oderRepository.updateByStatus(OrderStatus.sent, orderId);
        log.info("[{}] Pedido Enviado", orderId);
    }

    @Override
    public void processarEntregaDePedido(String orderId) {
        oderRepository.updateByStatus(OrderStatus.delivered, orderId);
        log.info("[{}] Pedido Entregue :)", orderId);
    }

    private void confirmar(String orderId) {
        Order order = oderRepository.updateByStatus(OrderStatus.confirmed, orderId);
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
