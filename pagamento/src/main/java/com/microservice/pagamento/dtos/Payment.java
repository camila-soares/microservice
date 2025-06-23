package com.microservice.pagamento.dtos;

import com.microservice.pagamento.dtos.enums.Brand;
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
