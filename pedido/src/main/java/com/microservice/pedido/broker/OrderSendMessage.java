package com.microservice.pedido.broker;

import com.microservice.pedido.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderSendMessage {

    public static String EXCHANGE_NAME ="pedido.exchange";

    public final RabbitTemplate rabbitTemplate;



    public void sendMessage( Order produto ) {
        System.out.println("Enviando mensagem");
        try {
            // String json = new ObjectMapper().w(produto, );
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, "", produto);
        } catch (AmqpException e) {
            e.getCause().printStackTrace();
        }

    }
}
