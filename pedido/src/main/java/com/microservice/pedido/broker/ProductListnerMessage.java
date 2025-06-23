package com.microservice.pedido.broker;

import com.microservice.pedido.config.MessageConfig;
import com.microservice.pedido.dto.ProductDTO;
import com.microservice.pedido.model.Order;
import com.microservice.pedido.model.Product;
import com.microservice.pedido.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductListnerMessage {

    private final ProductRepository produtoRepository;

    @RabbitListener(queues = MessageConfig.QUEUE)
    public void listiner( @Payload Product produtos ) {
        log.info("RECEBENDO O PRODUTO " + produtos);
        if (produtos == null) {
            log.warn("Product received is null, Ignoring...!");
            return;
        }
       log.info("PRODUTO é VALIDO" + produtos);
        produtoRepository.save(produtos);
    }
}
