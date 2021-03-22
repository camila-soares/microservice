package com.products.produtos.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutosDTO {

    private Long id;
    private String nome;
    private Integer estoque;
    private Double preco;
}
