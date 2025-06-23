package com.microservice.pedido.dto;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

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
