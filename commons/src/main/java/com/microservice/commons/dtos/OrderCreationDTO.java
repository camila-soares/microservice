package com.microservice.commons.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderCreationDTO {

    @Size(min = 1)
    @NotNull
    private List<@Valid ItemDto> items = new ArrayList<>();

    @NotNull
    private CustomerDTO Customer;

    @NotNull
    private PaymentDTO Payment;

    private String MerchantOrderId;


}
