package com.products.produtos.broker;


import com.microservice.commons.dtos.OrderDTO;
import com.products.produtos.services.ProdutosService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class ProdutoInput {

    private final ProdutosService service;


    @Bean
    public Consumer<OrderDTO> pedidoCriado(){
        return service::processarCriacaoDePedido;
    }
}
