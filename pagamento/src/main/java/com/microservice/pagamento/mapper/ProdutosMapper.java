package com.microservice.pagamento.mapper;

import com.microservice.pagamento.dtos.PageableDTO;
import com.microservice.pagamento.dtos.ProdutosDTO;
import com.microservice.pagamento.entity.Produtos;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class ProdutosMapper {

    public static ProdutosDTO toProdutoDTO( Produtos produtos ) {
        return ProdutosDTO.builder()
                .id( produtos.getId() )
                .nome( produtos.getNome() )
                .estoque( produtos.getEstoque())
                .build();
    }

    public static Produtos toProdutoEntity( ProdutosDTO produtosDTO ) {
        return Produtos.builder()
                .id( produtosDTO.getId() )
                .nome( produtosDTO.getNome()     )
                .estoque( produtosDTO.getEstoque() )
                .build();
    }


    public static Pageable getPageable( PageableDTO pageableDto ) {
        return PageRequest.of( pageableDto.getPageNumber(), pageableDto.getPageSize(),
                Sort.Direction.fromString( pageableDto.getSortDirection() ), pageableDto.getSortField() );
    }
}
