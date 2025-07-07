package com.microservice.fulfillments.broker;


import com.microservice.commons.dtos.OrderDTO;
import com.microservice.fulfillments.service.FulfillmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class FulfillmentInput {

    private final FulfillmentService service;


    @Bean
    public Consumer<OrderDTO> pagamentoAutorizado(){
        return service::processaPagamentoAutorizado;
    }
}
