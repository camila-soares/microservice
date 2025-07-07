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
public class CardOnFileDTO {

    private String usage  = "Used";
    private String reason = "Unscheduled";
}
