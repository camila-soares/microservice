package com.microservice.pagamento.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "payment")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    @Id
    private String id = UUID.randomUUID().toString();


    @ElementCollection
    @CollectionTable(name = "pedido_items",
            joinColumns = @JoinColumn(name = "pedido_id"))
    private List<Item> items = new ArrayList<>();


    @Embedded
    private Customer Customer;

    @Embedded
    private Payment payment;

    private String merchantOrderId;
}
