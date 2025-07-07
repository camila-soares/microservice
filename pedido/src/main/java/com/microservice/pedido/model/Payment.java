package com.microservice.pedido.model;

import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.extern.jackson.Jacksonized;


@Jacksonized
@Getter
@Setter
@Builder
@NoArgsConstructor
@Embeddable
@AllArgsConstructor
public class Payment {


    private String type;
    private AirlineData airlineData;
    private CreditCard creditCard;
    private String currency, interest;
    private int serviceTaxAmount, installments;
    private Boolean capture, authenticate,
            recurrent;
    private String softDescriptor;
    private Boolean tip, isCryptoCurrencyNegotiation;
    private Double amount;



}
