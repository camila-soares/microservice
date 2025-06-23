package com.microservice.pedido.dto;


import com.microservice.pedido.dto.enums.OrderStatus;
import lombok.*;


import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private String id;

    private Double totalAmount;

    private OrderStatus status;

    @Singular
    private List<ItemDto> items;

    private CustomerDTO customer;

    private PaymentDTO payment;

    private LocalDateTime createdAt, updatedAt;
}
