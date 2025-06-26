package com.microservice.commons.dtos;

import com.microservice.commons.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutosDTO {


    private String id;
    private String name;
    private Integer estoque;
    private Double preco;
    private Double totalAmount;
    private OrderStatus status = OrderStatus.received;


}
