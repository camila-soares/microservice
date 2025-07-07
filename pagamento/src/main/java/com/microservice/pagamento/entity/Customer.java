package com.microservice.pagamento.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;


@Jacksonized
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Embeddable
public class Customer {



    private String idCustomer;
    private String name, email, birthdate;

    private Address address;




}
