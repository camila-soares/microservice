package com.microservice.envio_acl.client;


import com.microservice.commons.exception.NotFoundException;
import com.microservice.commons.exception.Projeto1Exception;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Configuration
public class DeliverySystemHttpClientConfig {

    @Value("${deliverySystem.url}")
    private String url;

    @Bean
    public DeliverySystemHttpClient createClient() {
        WebClient webClient = WebClient.builder()
                .baseUrl(url)
                .defaultStatusHandler(
                        httpStatusCode -> HttpStatus.NOT_FOUND == httpStatusCode,
                        response -> Mono.error(new NotFoundException(HttpStatus.NOT_FOUND,"Conteúdo não encontrado!!")))
                .defaultStatusHandler(
                        HttpStatusCode::is5xxServerError,
                        response -> Mono.error(new Projeto1Exception(HttpStatus.resolve(response.statusCode().value()), "Ocorreu um erro inesperado")))
                .build();

        WebClientAdapter adapter = WebClientAdapter.create(webClient);

        return HttpServiceProxyFactory.builderFor(adapter)
                .build()
                .createClient(DeliverySystemHttpClient.class);
    }
}
