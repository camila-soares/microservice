package com.microservice.fulfillments.service;

import com.microservice.commons.dtos.OrderDTO;
import com.microservice.fulfillments.model.Fulfillment;

import java.time.LocalDate;

public interface FulfillmentService {

    void processaPagamentoAutorizado(OrderDTO order);
    Fulfillment pack(String orderId, LocalDate packagedAt);
}
