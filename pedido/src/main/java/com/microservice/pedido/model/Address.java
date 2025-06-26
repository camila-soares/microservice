package com.microservice.pedido.model;

import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Embeddable
@NoArgsConstructor
public class Address {


    private String street, number, postalCode, city, state;
}
