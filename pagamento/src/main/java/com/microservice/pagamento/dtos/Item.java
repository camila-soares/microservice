package com.microservice.pagamento.dtos;


import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Jacksonized
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Embeddable
@NoArgsConstructor
public class Item {

    private String productId;
    private Integer count;
    private BigDecimal price;
}
