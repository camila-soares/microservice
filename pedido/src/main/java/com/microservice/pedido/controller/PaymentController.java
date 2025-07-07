package com.microservice.pedido.controller;


import com.microservice.commons.dtos.OrderCreationDTO;
import com.microservice.commons.dtos.OrderDTO;
import com.microservice.pedido.mapper.OrderMapper;
import com.microservice.pedido.model.Order;
import com.microservice.pedido.service.OderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class PaymentController {

    private final OderService oderService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENTE')")
    public ResponseEntity<OrderDTO> createOrder(@AuthenticationPrincipal Jwt jwt, @RequestBody OrderCreationDTO dto) {
        String username = jwt.getTokenValue();
        Order order = OrderMapper.orderCreationDTO(dto);
        order =  oderService.create(order);
        OrderDTO orderDTO = OrderMapper.orderToOrderDTO(order);
        return ResponseEntity.accepted().body(orderDTO);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> findById(@PathVariable(name = "id") String id) {
        Order order = oderService.findById(id);

        return ResponseEntity.ok(OrderMapper.orderToOrderDTO(order));
    }


    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> findlOrders(@RequestParam("email") String email) {
        List<Order> list = oderService.findByEmail(email);
        List<OrderDTO> dtoList = new ArrayList<>();
        list.stream().map(OrderMapper::orderToOrderDTO).forEach(dtoList::add);
        return ResponseEntity.ok(dtoList);
    }
}
