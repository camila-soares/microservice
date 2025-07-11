package com.microservice.envio.broker;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.commons.dtos.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnvioOutput {

    private final StreamBridge streamBridge;

    private final ObjectMapper objectMapper;

    public void pedidoEnvioSolicitado(OrderDTO order) {
        enviarMensagem("pedidoEnvioSolicitado-out-0", order);
    }

    public void pedidoEnviado(OrderDTO order) {
        enviarMensagem("pedidoEnviado-out-0", order);
    }

    private void enviarMensagem(String bindingName, OrderDTO order) {
        try {
            streamBridge.send(bindingName, objectMapper.writeValueAsBytes(order));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
