package com.microservice.commons.dtos;

import com.microservice.commons.enums.Brand;
import lombok.*;
import lombok.extern.jackson.Jacksonized;


@Jacksonized
@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Data
@NoArgsConstructor
public class CreditCardDTO {

    public CardOnFileDTO cardOnFile;

    private String cardNumber, holder,expirationDate, securityCode,saveCard;
   
    private Brand brand;


}
