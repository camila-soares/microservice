package com.microservice.commons.enums;

import lombok.Getter;

@Getter
public enum Brand {
    Mastercard("Mastercard"),
    Visa("Visa"), Amex("Amex"), Elo("Elo"), Hipercard("Hipercard");

    private final String description;
    Brand(String description) {
        this.description = description;
    }
}
