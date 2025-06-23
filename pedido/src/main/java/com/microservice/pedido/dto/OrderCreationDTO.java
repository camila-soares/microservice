package com.microservice.pedido.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.List;

@Jacksonized
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderCreationDTO {

    @Size(min = 1)
    @NotNull
    private List<@Valid ItemDto> items = new ArrayList<>();

    @NotNull
    private CustomerDTO customer;

    @NotNull
    private PaymentDTO payment;
}
