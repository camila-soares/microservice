package com.microservice.fulfillments.model;


import com.microservice.commons.dtos.OrderDTO;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Jacksonized
@Builder
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "fulfillment")
public class Fulfillment {

    @Id
    private String id = UUID.randomUUID().toString();

    private String orderId;

    @Setter
    private LocalDate packagedAt;

    private OrderDTO originDto;
}
