package com.microservice.invetoryms.broker;


import com.microservice.commons.dtos.OrderDTO;
import com.microservice.invetoryms.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class InventoryInput {

    private final InventoryService service;

    @Bean
    public Consumer<OrderDTO> pedidoCriado() {
        return service::processarCriacaoDePedido;
    }

}
