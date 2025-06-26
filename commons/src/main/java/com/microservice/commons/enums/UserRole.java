package com.microservice.commons.enums;


import lombok.Getter;

@Getter
public enum UserRole {

    ADMIN("ADMIN"),
    GERENTE("GERENTE"),
    CLIENTE("CLIENTE");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }
}
