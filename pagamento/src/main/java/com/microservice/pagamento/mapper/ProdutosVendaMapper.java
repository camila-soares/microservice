package com.microservice.pagamento.mapper;

import com.microservice.pagamento.dtos.PageableDTO;
import com.microservice.pagamento.dtos.ProdutosVendaDTO;
import com.microservice.pagamento.entity.ProdutoVenda;
import com.microservice.pagamento.entity.Venda;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class ProdutosVendaMapper {

    public static ProdutoVenda toProdutoDTO( ProdutoVenda produtoVenda ) {
        return ProdutoVenda.builder()
                .id( produtoVenda.getId() )
                .idProduto( produtoVenda.getIdProduto() )
                .quantidade( produtoVenda.getQuantidade() )
                .build();
    }

    public static ProdutoVenda create(ProdutosVendaDTO produtosVendaDTO) {
        return new ModelMapper().map(produtosVendaDTO, ProdutoVenda.class);
    }


    public static ProdutoVenda toProdutoEntity( ProdutosVendaDTO produtosVendaDTO ) {
        return ProdutoVenda.builder()
                //.id( produtosVendaDTO.getId() )
                .idProduto( produtosVendaDTO.getIdProduto() )
                .quantidade( produtosVendaDTO.getQuantidade() )
                .build();
    }


    public static ProdutosVendaDTO userDetailsDto( Venda produtos ) {
        return ProdutosVendaDTO.builder()
                .build();
    }

    public static Pageable getPageable( PageableDTO pageableDto ) {
        return PageRequest.of( pageableDto.getPageNumber(), pageableDto.getPageSize(),
                Sort.Direction.fromString( pageableDto.getSortDirection() ), pageableDto.getSortField() );
    }
}
