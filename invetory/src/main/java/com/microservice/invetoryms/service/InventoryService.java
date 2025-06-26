package com.microservice.invetoryms.service;

import com.microservice.commons.dtos.OrderDTO;

public interface InventoryService {
    void processarCriacaoDePedido(OrderDTO order);
}
