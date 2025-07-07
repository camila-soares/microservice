package com.microservice.envio.service.impl;


import com.microservice.commons.dtos.OrderDTO;
import com.microservice.commons.exception.NotFoundException;
import com.microservice.envio.broker.EnvioOutput;
import com.microservice.envio.model.Envio;
import com.microservice.envio.repository.EnvioRepository;
import com.microservice.envio.service.EnvioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnvioServiceImpl implements EnvioService {

    private final EnvioRepository repository;

    private final EnvioOutput output;


    @Override
    public void processarPedidoEmbalado(OrderDTO order) {
        repository.save(Envio.builder()
                .orderId(order.getId()).build());

        output.pedidoEnvioSolicitado(order);
        log.info("Pedido envio Solicitado", order.getId());

    }

    @Override
    public void processarEnvioConfirmado(OrderDTO order) {
        Envio envio = repository.findById(order.getId()).orElseThrow(()
                -> new NotFoundException(HttpStatus.NOT_FOUND, "Pedido [%s] não foi encontrado", order.getId()));

        envio.setDeliveryRequestedOn(LocalDate.now());
        repository.save(envio);

        output.pedidoEnviado(order);
        log.info("[%s] Pedido de envio confirmado", envio.getOrderId());
    }
}
