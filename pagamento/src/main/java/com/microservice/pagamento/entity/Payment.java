package com.microservice.pagamento.entity;

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

    private String type = "CreditCard";
    private AirlineData airlineData;
    private CreditCard creditCard;
    private String currency, Country;
    private int serviceTaxAmount = 0;
    private int installments = 0;
    private String interest;
    private boolean capture = true;
    private boolean authenticate = false;
    private boolean recurrent = false;
    private String softDescriptor = "123456789ABCD";
    private boolean tip = false;
    private Boolean isCryptoCurrencyNegotiation = true;
    private Double amount;

}
