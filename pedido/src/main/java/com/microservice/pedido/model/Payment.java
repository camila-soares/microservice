package com.microservice.pedido.model;

import com.microservice.commons.enums.Brand;
import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.extern.jackson.Jacksonized;


@Jacksonized
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Payment {


    private String cardId, bin, numberToken, cardholderName, securityCode, expirationMonth, expirationYear;

    private Brand brand;

}
