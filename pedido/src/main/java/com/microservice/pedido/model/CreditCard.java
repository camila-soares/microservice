package com.microservice.pedido.model;


import com.microservice.commons.enums.Brand;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class CreditCard {

    public CardOnFile cardOnFile;
    private String cardNumber, holder,expirationDate, securityCode,saveCard;

    @Enumerated(EnumType.STRING)
    private Brand brand;


}
