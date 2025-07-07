package com.microservice.pedido.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;


@Jacksonized
@Getter
@Setter
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Customer {




    private String idCustomer;
    private String name, email, birthdate;

    private Address address;//,DeliveryAddress, Billing;


}
