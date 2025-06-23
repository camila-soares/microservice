package com.microservice.pedido.dto;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;


@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable {

    private String id;
    private String nome;
    private Integer estoque;
}
