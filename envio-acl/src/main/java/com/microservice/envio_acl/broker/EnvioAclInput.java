package com.microservice.envio_acl.broker;

import com.microservice.commons.dtos.OrderDTO;
import com.microservice.envio_acl.service.EnvioAclService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;


@Service
@RequiredArgsConstructor
public class EnvioAclInput {

    private final EnvioAclService service;


    @Bean
    public Consumer<OrderDTO> pedidoEnvioSolicitado() {
        return service::processarPedidoEnvioSolicitado;
    }

}
