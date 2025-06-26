package com.products.produtos.mapper;

import com.microservice.commons.dtos.PageableDTO;
import com.microservice.commons.dtos.ProdutosDTO;
import com.products.produtos.entity.Produtos;
import org.mapstruct.Mapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Mapper(componentModel = "spring")
public interface ProdutosMapper {

    ProdutosDTO toProdutoDTO(Produtos produtos );

    Produtos toProdutoEntity( ProdutosDTO produtosDTO );

    public static ProdutosDTO userDetailsDto( String nome ) {
        return ProdutosDTO.builder()
                .name( nome )
                .build();
    }

    default Pageable getPageable(PageableDTO pageableDto) {
        return PageRequest.of( pageableDto.getPageNumber(), pageableDto.getPageSize(),
                Sort.Direction.fromString( pageableDto.getSortDirection() ), pageableDto.getSortField() );
    }
}
