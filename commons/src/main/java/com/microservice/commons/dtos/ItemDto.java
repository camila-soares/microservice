package com.microservice.commons.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ItemDto {

    //@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$")
    @NotNull
    private String productId;
    @Positive
    @NotNull
    private Integer count;
    @Positive
    @NotNull
    private Double price;
}
