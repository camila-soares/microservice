package com.microservice.pedido.model;


import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
public class CardOnFile {

    private String usage  = "Used";
    private String reason = "Unscheduled";
}
