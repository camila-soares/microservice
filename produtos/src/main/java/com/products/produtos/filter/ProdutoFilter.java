package com.products.produtos.filter;

import com.products.produtos.dtos.ProdutosDTO;
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
                .nome( produtosDTO.getNome() )
                .build();
    }
}
