package com.products.produtos.mapper;

import com.products.produtos.dtos.PageableDTO;
import com.products.produtos.dtos.ProdutosDTO;
import com.products.produtos.entity.Produtos;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class ProdutosMapper {

    public static ProdutosDTO toProdutoDTO( Produtos produtos ) {
        return ProdutosDTO.builder()
                .id( produtos.getId() )
                .nome( produtos.getNome() )
                .estoque( produtos.getEstoque() )
                .preco( produtos.getPreco() )
                .build();
    }

    public static Produtos toProdutoEntity( ProdutosDTO produtosDTO ) {
        return Produtos.builder()
                .id( produtosDTO.getId() )
                .nome( produtosDTO.getNome() )
                .estoque( produtosDTO.getEstoque() )
                .preco( produtosDTO.getPreco() )
                .build();
    }

    public static ProdutosDTO userDetailsDto( String nome ) {
        return ProdutosDTO.builder()
                .nome( nome )
                .build();
    }

    public static Pageable getPageable( PageableDTO pageableDto ) {
        return PageRequest.of( pageableDto.getPageNumber(), pageableDto.getPageSize(),
                Sort.Direction.fromString( pageableDto.getSortDirection() ), pageableDto.getSortField() );
    }
}
