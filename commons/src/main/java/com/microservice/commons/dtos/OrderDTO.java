package com.microservice.commons.dtos;


import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class OrderDTO {

    private String id;

    @Singular
    private List<ItemDto> items;

    private CustomerDTO customer;

    private PaymentDTO payment;
    private String merchantOrderId;


}
