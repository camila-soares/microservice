package com.microservice.envio_acl.client.dto;

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
public class DeliveryResponseDto {

    private String id;

    private DeliveryStatus status;
}
