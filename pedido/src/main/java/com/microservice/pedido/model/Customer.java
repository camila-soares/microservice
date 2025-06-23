package com.microservice.pedido.model;


import com.microservice.pedido.dto.Address;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;


@Jacksonized
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
public class Customer {

    private String firstName, lastName, email;

    @Embedded
    private Address deliveryAddress;
}
