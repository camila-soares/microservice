package com.microservice.pagamento.broker;


import com.microservice.commons.dtos.OrderDTO;

import com.microservice.pagamento.services.impl.PaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class PaymentInput {

    private final PaymentServiceImpl service;


    @Bean
    public Consumer<OrderDTO> pedidoConfirmado() {
        return service::processaPedidoConfirmado;
    }

}
