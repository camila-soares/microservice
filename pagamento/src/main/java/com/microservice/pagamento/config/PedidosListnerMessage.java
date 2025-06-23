package com.microservice.pagamento.config;

import com.microservice.pagamento.entity.Pedido;
import com.microservice.pagamento.repositories.PedidoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PedidosListnerMessage {

    @Autowired
    private PedidoRepository produtosRepository;


    @RabbitListener(queues = MessageConfig.QUEUE)
    public void listiner( @Payload Pedido produtos ) {
        System.out.println("RECEBENDO O PEDIDO " + produtos);
       // simpMessagingTemplate.convertAndSend(produtos.getClass());
        produtosRepository.save(produtos);
    }
}
