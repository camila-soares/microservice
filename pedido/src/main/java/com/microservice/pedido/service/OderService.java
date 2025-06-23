package com.microservice.pedido.service;


import com.microservice.pedido.model.Order;
import com.microservice.pedido.model.Payment;


public interface OderService {

    Order create(Order order);

    Order findById(String id);

    void processarReservaDePedido(String orderId);

    void processarQualificacaoDePedido(String orderId);

    void processarRecusaDePedido(String orderId);

    void processarPagamentoAutorizado(String orderId, Payment payment);

    void processarPagamentoNaoAutorizado(String orderId, Payment payment);

    void processarEnvioDePedido(String orderId);

    void processarEntregaDePedido(String orderId);
}
