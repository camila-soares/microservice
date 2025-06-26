package com.microservice.pagamento.config;

import com.microservice.pagamento.entity.Pedido;
import com.microservice.pagamento.repositories.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProdutosListnerMessage {

   private final PedidoRepository pedidoRepository;

    @RabbitListener(queues = {"${pagamento.rabbitmq.queue}"})
    public void listiner( @Payload Pedido pedido ) {
        System.out.println("RECEBENDO O PEDIDO " + pedido);
        pedidoRepository.save(pedido);
    }
}
