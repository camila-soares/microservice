package com.microservice.pagamento.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutosDTO {

    private Long id;
    private String nome;
    private Integer estoque;
}
