package com.microservice.pedido.model;

import com.microservice.pedido.dto.enums.Brand;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
