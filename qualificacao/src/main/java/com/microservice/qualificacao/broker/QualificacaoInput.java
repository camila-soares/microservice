package com.microservice.qualificacao.broker;

import com.microservice.commons.dtos.OrderDTO;
import com.microservice.qualificacao.service.QualificacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class QualificacaoInput {

    private final QualificacaoService service;

    @Bean
    public Consumer<OrderDTO> pedidoCriado(){
        return service::processarCriacaoDePedido;
    }
}
