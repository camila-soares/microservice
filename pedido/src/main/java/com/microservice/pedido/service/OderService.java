package com.microservice.pedido.service;


import com.microservice.commons.dtos.OrderCreationDTO;
import com.microservice.pedido.model.Order;
import com.microservice.pedido.model.Payment;

import java.util.List;


public interface OderService {

    Order create(Order order);

    Order findById(String id);

    List<Order> findByEmail(String email);

    void processarReservaDePedido(String orderId);

    void processarQualificacaoDePedido(String orderId);

    void processarRecusaDePedido(String orderId);

    void processarPagamentoAutorizado(String orderId, Payment payment);

    void processarPagamentoNaoAutorizado(String orderId, Payment payment);

    void processarEnvioDePedido(String orderId);

    void processarEntregaDePedido(String orderId);
}
