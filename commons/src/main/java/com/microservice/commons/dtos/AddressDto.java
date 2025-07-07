package com.microservice.commons.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Data
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class AddressDto {

    @NotBlank
    private String street;

    @NotBlank
    private String number;

    @NotBlank
    private String complement;

    @NotBlank
    private String zipCode;

    @NotBlank
    private String city;

    private String state;
    private String country;
    private String neighborhood;


}
