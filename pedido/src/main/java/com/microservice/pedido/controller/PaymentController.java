package com.microservice.pedido.controller;


import com.microservice.pedido.dto.OrderCreationDTO;
import com.microservice.pedido.dto.OrderDTO;
import com.microservice.pedido.mapper.OrderMapper;
import com.microservice.pedido.model.Order;
import com.microservice.pedido.service.OderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class PaymentController {


    private final OderService oderService;
    private final OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderCreationDTO order) {
        Order order1 = oderService.create(orderMapper.toEntity(order));
        return ResponseEntity.accepted().body(orderMapper.toDTO(order1));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> findById(@PathVariable(name = "id") String id) {
        Order order = oderService.findById(id);

        return ResponseEntity.ok(orderMapper.toDTO(order));
    }
}
