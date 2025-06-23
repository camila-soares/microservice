package com.microservice.pagamento.dtos;



import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
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
