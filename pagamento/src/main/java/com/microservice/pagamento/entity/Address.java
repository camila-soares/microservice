package com.microservice.pagamento.entity;

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


    private String Street, Number, Complement, ZipCode,Neighborhood, City, State;

}
