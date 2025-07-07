package com.microservice.fulfillments;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableRabbit
@EnableDiscoveryClient
public class FulfillmentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FulfillmentsApplication.class, args);
	}

}
