package com.microservice.envio.broker;


import com.microservice.commons.dtos.OrderDTO;
import com.microservice.envio.service.EnvioService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class EnvioInput {

    private final EnvioService service;

    @Bean
    public Consumer<OrderDTO> pedidoEmbalado() {
        return service::processarPedidoEmbalado;
    }


    @Bean
    public Consumer<OrderDTO> pedidoEnvioConfirmado() {
        return service::processarEnvioConfirmado;
    }
}
