package com.microservice.commons.dtos;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {


    private String type;
    private AirlineDataDTO airlineData;
    private CreditCardDTO creditCard;
    private String currency, country;
    private int serviceTaxAmount;
    private int installments;
    private String interest;
    private Boolean capture;
    private Boolean authenticate;
    private Boolean recurrent;
    private String softDescriptor;
    private Boolean tip;
    private Boolean isCryptoCurrencyNegotiation;
    private Double amount;
}