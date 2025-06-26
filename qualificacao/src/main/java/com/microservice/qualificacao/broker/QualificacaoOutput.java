package com.microservice.qualificacao.broker;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.commons.dtos.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.cloud.stream.function.StreamBridge;

@Service
@RequiredArgsConstructor
public class QualificacaoOutput {

    private final StreamBridge streamBridge;
    private final ObjectMapper objectMapper;

    public void pedidoQualificado(OrderDTO order) {
        enviarMensagem("pedidoQualificado-out-0", order);
    }

    public void pedidoRecusado(OrderDTO order) {
        enviarMensagem("pedidoRecusado-out-0", order);
    }

    private void enviarMensagem(String bindingName, OrderDTO order) {
        try {
            streamBridge.send(bindingName, objectMapper.writeValueAsBytes(order));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
