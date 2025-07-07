package com.microservice.fulfillments.service.impl;


import com.microservice.commons.dtos.OrderDTO;
import com.microservice.commons.exception.BusinessException;
import com.microservice.fulfillments.broker.FulfillmentOutput;
import com.microservice.fulfillments.model.Fulfillment;
import com.microservice.fulfillments.repository.FulFillmentRepository;
import com.microservice.fulfillments.service.FulfillmentService;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class FulfillmentServiceImpl implements FulfillmentService {

    private final FulFillmentRepository repository;
    private final FulfillmentOutput output;

    @Override
    public void processaPagamentoAutorizado(OrderDTO order) {
        repository.save(Fulfillment.builder()
                .orderId(order.getId())
                .originDto(order)
                .build());

    }

    @Override
    public Fulfillment pack(String orderId, LocalDate packagedAt) {

        Fulfillment fulfillment = repository.findByOrderId(orderId).stream().findFirst()
                .orElseThrow(() -> new NotFoundException("Pedido not found"));

        if (fulfillment.getPackagedAt()  != null)
            throw new RuntimeException( "Pacote já foi embalado para o pedido [%s] " + orderId);

        fulfillment.setPackagedAt(packagedAt);
        repository.save(fulfillment);
        output.pedidoEmbalado(fulfillment.getOriginDto());
        log.info("[%s] Pedido embalado com sucesso", orderId);

        return fulfillment;
    }
}
