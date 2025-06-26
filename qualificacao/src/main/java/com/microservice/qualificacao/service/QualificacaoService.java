package com.microservice.qualificacao.service;

import com.microservice.commons.dtos.OrderDTO;

public interface QualificacaoService {
    void processarCriacaoDePedido(OrderDTO order);
}
