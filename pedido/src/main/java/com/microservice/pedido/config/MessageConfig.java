//package com.microservice.pedido.config;
//
//
//import com.microservice.pedido.model.Product;
//import com.rabbitmq.client.ConnectionFactory;
//import lombok.RequiredArgsConstructor;
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.DefaultClassMapper;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.integration.annotation.ServiceActivator;
//import org.springframework.messaging.Message;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@RequiredArgsConstructor
//public class MessageConfig {
//
//    public static final String EXCHANGE_NAME = "produtos.exchange";
//    public static final String QUEUE = "produto.queue";
//
//    public static final String ROUTING_KEY = "";
//
//
//    @Bean
//    public Exchange declareExchange() {
//        return ExchangeBuilder.directExchange(EXCHANGE_NAME).durable( true ).build();
//    }
//
//    @Bean
//    public Queue declareQueue(){
//        return QueueBuilder.durable(QUEUE).build();
//    }
//
//
//    @Bean
//    public Binding declareBiding(Exchange exchange, Queue queue){
//        return BindingBuilder.bind(queue)
//                .to(exchange)
//                .with(ROUTING_KEY)
//                .noargs();
//    }
//
//
//
//    @Bean
//    public Jackson2JsonMessageConverter jsonMessageConverter() {
//        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
//
//        DefaultClassMapper classMapper = new DefaultClassMapper();
//        Map<String, Class<?>> idClassMapping = new HashMap<>();
//        idClassMapping.put("com.products.produtos.entity.Produtos", Product.class);
//
//        classMapper.setIdClassMapping(idClassMapping);
//        converter.setClassMapper(classMapper);
//
//        return converter;
//    }
//
//
//
//}
