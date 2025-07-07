package com.products.produtos.config;


import org.springframework.amqp.core.FanoutExchange;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public FanoutExchange produtoProcessingErrorExchange() {
        return new FanoutExchange("produto-processing-error", true, false);
    }
//
//    @Bean
//    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
//        return new RabbitAdmin(connectionFactory);
//    }
//
//    @Bean
//    public ApplicationRunner runner(RabbitAdmin rabbitAdmin) {
//        return args -> rabbitAdmin.initialize();
//    }
}
