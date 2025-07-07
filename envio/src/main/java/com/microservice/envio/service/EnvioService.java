package com.microservice.envio.service;

import com.microservice.commons.dtos.OrderDTO;

public interface EnvioService {

    void processarPedidoEmbalado(OrderDTO orderDTO);
    void processarEnvioConfirmado(OrderDTO orderDTO);
}
