package com.microservice.pedido.model;



import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.extern.jackson.Jacksonized;


@Jacksonized
@Data
@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Embeddable
public class Item {

    private String productId;
    private Integer count;
    private Double price;
}
