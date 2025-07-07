package com.microservice.qualificacao.service.impl;


import com.microservice.commons.dtos.OrderDTO;
import com.microservice.qualificacao.broker.QualificacaoOutput;
import com.microservice.qualificacao.model.State;
import com.microservice.qualificacao.repository.StateRepository;
import com.microservice.qualificacao.service.QualificacaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class QualificacaoServiceImpl implements QualificacaoService {

    private final StateRepository stateRepository;
    private final QualificacaoOutput output;

    @Override
    public void processarCriacaoDePedido(OrderDTO order) {
        State state = stateRepository.findByAcronym(order.getCustomer().getAddress().getState());

        if (state != null) {
            output.pedidoQualificado(order);
            log.info("[{}] Qualificação do pedido concluida", order.getId());
        } else {
            output.pedidoRecusado(order);
            log.info("[{}] Qualificação do pedido recusada", order.getId());
        }
    }
}
