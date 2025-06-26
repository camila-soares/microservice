package com.microservice.commons.dtos;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
