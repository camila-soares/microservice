//package com.products.produtos.config;
//
//import org.springframework.amqp.core.Exchange;
//import org.springframework.amqp.core.ExchangeBuilder;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class MessageConfig {
//
//    public static String EXCHANGE_NAME ="produtos.exchange";
//
//    @Bean
//    public Exchange declareExchange() {
//        return ExchangeBuilder.directExchange( EXCHANGE_NAME ).durable( true ).build();
//    }
//
//    @Bean
//    public MessageConverter jsonMessageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
//}
