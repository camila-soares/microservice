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
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
public class CreditCard {

    public CardOnFile CardOnFile;
    private String CardNumber, Holder,ExpirationDate,
            SecurityCode,SaveCard, Brand;
}
