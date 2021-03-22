package com.microservice.pagamento.config;

import com.microservice.pagamento.dtos.ProdutosDTO;
import com.microservice.pagamento.entity.Produtos;
import com.microservice.pagamento.mapper.ProdutosMapper;
import com.microservice.pagamento.repositories.ProdutosRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProdutosListnerMessage {

    @Autowired
    private ProdutosRepository produtosRepository;

    @RabbitListener(queues = {"${pagamento.rabbitmq.queue}"})
    public void listiner( @Payload Produtos produtos ) {
        System.out.println("RECEBENDO O PRODUTO " + produtos);
        produtosRepository.save(produtos);
    }
}
