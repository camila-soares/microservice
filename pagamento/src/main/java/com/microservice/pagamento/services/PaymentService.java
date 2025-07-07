package com.microservice.pagamento.services;

import com.microservice.commons.dtos.OrderDTO;

public interface PaymentService {

    void processaPedidoConfirmado(OrderDTO orderDTO);
}
