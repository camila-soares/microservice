package com.microservice.invetoryms;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableRabbit
public class InvetoryMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvetoryMsApplication.class, args);
    }

}
