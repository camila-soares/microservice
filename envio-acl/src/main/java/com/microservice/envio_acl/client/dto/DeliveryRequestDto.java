package com.microservice.envio_acl.client.dto;


import com.microservice.commons.dtos.CustomerDTO;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class DeliveryRequestDto {

    private String orderId;

    private CustomerDTO customer;
}
