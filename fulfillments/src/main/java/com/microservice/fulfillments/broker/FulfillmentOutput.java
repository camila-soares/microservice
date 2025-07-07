package com.microservice.fulfillments.broker;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.commons.dtos.OrderDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FulfillmentOutput {

    public final StreamBridge streamBridge;
    private final ObjectMapper objectMapper;

    public void pedidoEmbalado(OrderDTO order) {
        log.info("Pedido embalado");
        enviarMensagem("pedidoEmbalado-out-0", order);

    }

    private void enviarMensagem(String bindingName, OrderDTO order) {
        try {
            streamBridge.send(bindingName, objectMapper.writeValueAsBytes(order));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
