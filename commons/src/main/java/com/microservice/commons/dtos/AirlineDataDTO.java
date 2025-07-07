package com.microservice.commons.dtos;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class AirlineDataDTO {

    private String ticketNumber = "";
}