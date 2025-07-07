package com.microservice.commons.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;


@Jacksonized
@Builder
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private String idCustomer = UUID.randomUUID().toString();

    private String name;

    @Email
    @NotNull
    private String email;

    private String birthdate;

    @NotNull
    private AddressDto address;



//    private AddressDto DeliveryAddress;
//    private AddressDto Billing;

}
