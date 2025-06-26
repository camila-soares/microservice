package com.products.produtos.filter;

import com.microservice.commons.dtos.ProdutosDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Builder
@AllArgsConstructor
@Getter
public class ProdutoFilter {

    private String nome;

    public static ProdutoFilter of( ProdutosDTO produtosDTO ) {
        return ProdutoFilter.builder()
                .nome( produtosDTO.getName() )
                .build();
    }
}
