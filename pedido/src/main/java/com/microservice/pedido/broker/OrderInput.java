package com.microservice.pedido.broker;


import com.microservice.commons.dtos.OrderDTO;
import com.microservice.commons.dtos.OrderDeliveryDto;
import com.microservice.pedido.mapper.OrderMapper;
import com.microservice.pedido.model.Order;
import com.microservice.pedido.service.OderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class OrderInput {

   private final OderService service;
   private final OrderMapper orderMapper;

    @Bean
    public Consumer<OrderDTO> pedidoQualificado() {
       return order -> service.processarQualificacaoDePedido(order.getId());
    }

    @Bean
    public Consumer<OrderDTO> pedidoReservado() {
        return order -> service.processarReservaDePedido(order.getId());
    }

    @Bean
    public Consumer<OrderDTO> pedidoRecusado() {
        return order -> service.processarRecusaDePedido(order.getId());
    }

    @Bean
    public Consumer<OrderDTO> pagamentoAutorizado() {
        return order -> service.processarPagamentoAutorizado(order.getId(), orderMapper.toPayment(order.getPayment()));
    }

    @Bean
    public Consumer<OrderDTO> pagamentoNegado() {
        return order -> service.processarPagamentoNaoAutorizado(order.getId(), orderMapper.toPayment(order.getPayment()));
    }


    @Bean
    public Consumer<Order> pedidoEnviado() {
        return order -> service.processarEnvioDePedido(order.getId());
    }

    @Bean
    public Consumer<OrderDeliveryDto> pedidoEntregue() {
        return order -> service.processarEntregaDePedido(order.getOrderId());
    }

    @Bean
    public Consumer<Message<byte[]>> pedidoEntregueDlq() {
        return message -> {
            System.err.println("⚠️ Mensagem falhou e foi para a DLQ: " + new String(message.getPayload()));
            // salvar em log, reprocessar ou armazenar em base
        };
    }
}
