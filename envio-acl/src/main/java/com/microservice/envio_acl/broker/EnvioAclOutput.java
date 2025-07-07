package com.microservice.envio_acl.broker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.commons.dtos.OrderDTO;
import com.microservice.commons.dtos.OrderDeliveryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnvioAclOutput {

    private final StreamBridge streamBridge;

    private final ObjectMapper objectMapper;

    public void pedidoEnvioConfirmado(OrderDTO order) {
        enviarMensagem("pedidoEnvioConfirmado-out-0", order);
    }

    public void pedidoEntregue(OrderDeliveryDto delivery) {
        enviarMensagem("pedidoEntregue-out-0", delivery);
    }

    private void enviarMensagem(String bindingName, Object object) {
        try {
            streamBridge.send(bindingName, objectMapper.writeValueAsBytes(object));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
