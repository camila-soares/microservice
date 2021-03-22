package com.products.produtos.config;

import com.products.produtos.dtos.ProdutosDTO;
import com.products.produtos.entity.Produtos;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProdutosSendMessage {

    @Value( "${produtos.rabbitmq.exchange}" )
    String exchange;

    @Value( "${produtos.rabbitmq.routingkey}" )
    String routingkey;

    public final RabbitTemplate rabbitTemplate;

    @Autowired
    public ProdutosSendMessage( RabbitTemplate rabbitTemplate ) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage( Produtos produto ) {
        rabbitTemplate.convertAndSend(exchange, routingkey, produto );
    }
}
