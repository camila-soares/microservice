package com.microservice.pagamento.entity;


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

    private String Usage  = "Used";
    private String Reason = "Unscheduled";
}
