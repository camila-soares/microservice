package com.microservice.pedido.model;

import com.microservice.commons.dtos.AddressDto;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Embeddable
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Address {

    private String street, number, complement, zipCode, city, state, country, neighborhood;



//    public AddressDto addressDto(Address address) {
//        return new AddressDto(address.street,
//                address.number, address.complement
//        ,address.zipCode, address.city, address.state, address.country, address.neighborhood);
//
//    }

//    public Address dtoToAddress(AddressDto address) {
//        return new Address(address.getStreet(),
//                address.getNumber(), address.getComplement()
//                ,address.getZipCode(), address.getCity(),
//                address.getState(), address.getCountry(), address.getNeighborhood());
//
//    }
}
