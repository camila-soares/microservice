package com.microservice.invetoryms.service.impl;


import com.microservice.commons.dtos.OrderDTO;
import com.microservice.invetoryms.broker.InventoryOutput;
import com.microservice.invetoryms.exception.UnavailableItemsException;
import com.microservice.invetoryms.model.Inventory;
import com.microservice.invetoryms.repository.InventoryRepository;
import com.microservice.invetoryms.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository repository;
    private final InventoryOutput output;

    @Override
    public void processarCriacaoDePedido(OrderDTO order) {
        try {
            order.getItems().forEach(item -> {
                Inventory inventory = repository
                        .findById(item.getProductId())
                        .orElseThrow(UnavailableItemsException::new);

                inventory.sell(item.getCount());
                repository.save(inventory);
            });
            output.pedidoReservado(order);
            log.info("[{}] Reserva do pedido concluida", order.getId());
        } catch (UnavailableItemsException e) {
            output.pedidoRecusado(order);
            log.info("[{}] Reserva de pedido recusada", order.getId());
        }
    }
}
