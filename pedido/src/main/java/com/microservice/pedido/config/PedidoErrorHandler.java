package com.microservice.pedido.config;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class PedidoErrorHandler {

    @ServiceActivator(inputChannel = "pedidoEntregue-in-0.errors")
    public void handleError(Message<?> message) {
        System.err.println("Erro ao processar mensagem do canal pedidoEntregue: " + message);
        // Aqui você pode fazer log, enviar para uma DLQ, etc.
    }
}
