package com.microservice.pagamento.mapper;

import com.microservice.pagamento.dtos.PageableDTO;
import com.microservice.pagamento.dtos.ProdutosDTO;
import com.microservice.pagamento.entity.Pedido;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class ProdutosMapper {

    public static ProdutosDTO toProdutoDTO( Pedido produtos ) {
        return ProdutosDTO.builder()
                .id( produtos.getId() )
                .status( produtos.getStatus() )
                .totalAmount( produtos.getTotalAmount())
                .build();
    }

    public static Pedido toProdutoEntity(ProdutosDTO produtosDTO ) {
        return Pedido.builder()
                .id( produtosDTO.getId() )
                .status( produtosDTO.getStatus()     )
                .totalAmount( produtosDTO.getTotalAmount() )
                .build();
    }


    public static Pageable getPageable( PageableDTO pageableDto ) {
        return PageRequest.of( pageableDto.getPageNumber(), pageableDto.getPageSize(),
                Sort.Direction.fromString( pageableDto.getSortDirection() ), pageableDto.getSortField() );
    }
}
