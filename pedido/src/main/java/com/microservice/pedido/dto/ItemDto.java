package com.microservice.pedido.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;
import org.jetbrains.annotations.NotNull;
import jakarta.validation.constraints.Positive;

@Jacksonized
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class ItemDto {

    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$")
    @NotNull
    private String productId;
    @Positive
    @NotNull
    private Integer count;
    @Positive
    @NotNull
    private Double price;
}
