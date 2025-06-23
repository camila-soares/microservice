package com.microservice.pagamento.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    public static final String EXCHANGE_NAME = "pedido.exchange";

    public static final String QUEUE = "pedido.queue";

    public static final String ROUTING_KEY = "";

    @Bean
    public Exchange declareExchange() {
        return ExchangeBuilder.directExchange( EXCHANGE_NAME ).durable( true ).build();
    }

    @Bean
    public Queue declareQueue(){
        return QueueBuilder.durable(QUEUE).build();
    }

    @Bean
    public Binding declareBiding(Exchange exchange, Queue queue){
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(ROUTING_KEY)
                .noargs();
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

