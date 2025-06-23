package com.microservice.pedido.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Jacksonized
@Builder
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderDeliveryDto {

    private String orderId;

    private LocalDate deliveredAt;
}
