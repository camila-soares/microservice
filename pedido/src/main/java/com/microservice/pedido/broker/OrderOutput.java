package com.microservice.pedido.broker;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.pedido.mapper.OrderMapper;
import com.microservice.pedido.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderOutput {

    private final StreamBridge streamBridge;

    private final ObjectMapper objectMapper;


    public void pedidoCriado(Order order) {
        enviarMensagem("pedidoCriado-out-0", order);
    }

    public void pedidoConfirmado(Order order) {
        enviarMensagem("pedidoConfirmado-out-0", order);
    }

    private void enviarMensagem(String bindingName, Order order) {
        try {
            streamBridge.send(bindingName, objectMapper.writeValueAsBytes(OrderMapper.orderToOrderDTO(order)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
