package com.microservice.fulfillments.controller;


import com.microservice.commons.dtos.OrderPackingDTO;
import com.microservice.fulfillments.model.Fulfillment;
import com.microservice.commons.dtos.FulfillmentDto;
import com.microservice.fulfillments.mapper.FulfillmentMapper;
import com.microservice.fulfillments.service.FulfillmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class FulfillmentController {

    private final FulfillmentService service;


    @PatchMapping(value = "/pedidos/{orderId}/packaging", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FulfillmentDto> findById(@PathVariable(name = "orderId") String orderId,
                                                   @Valid @RequestBody OrderPackingDTO orderPackingDto) {
        Fulfillment fulfillment = service.pack(orderId, orderPackingDto.getPackagedAt());

        return ResponseEntity.ok(FulfillmentMapper.toDto(fulfillment));
    }
}
