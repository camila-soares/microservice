package com.microservice.commons.enums;


import lombok.Getter;

@Getter
public enum OrderStatus {

    received("received"),
    confirmed("confirmed"),
    delivered("delivered"),
    sent("sent"),
    cancelled("cancelled"),
    in_preparation("in_preparation");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }





}
