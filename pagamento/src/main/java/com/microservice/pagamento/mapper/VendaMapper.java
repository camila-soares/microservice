package com.microservice.pagamento.mapper;

import com.microservice.pagamento.dtos.PageableDTO;
import com.microservice.pagamento.dtos.VendaDTO;
import com.microservice.pagamento.entity.ProdutoVenda;
import com.microservice.pagamento.entity.Venda;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

public class VendaMapper {

    public static VendaDTO toProdutoDTO( Venda venda ) {
        return VendaDTO.builder()
                .id( venda.getId() )
                .data( venda.getData() )
                .valorTotal( venda.getValorTotal() )
                .build();
    }

    public static Venda toProdutoEntity( VendaDTO vendaDTO) {
        return Venda.builder()
                .id( vendaDTO.getId() )
                .data( vendaDTO.getData() )
                .valorTotal( vendaDTO.getValorTotal() )
                .build();
    }

    public static VendaDTO userDetailsDto( String produtos ) {
        return VendaDTO.builder()
                .build();
    }

    public static Pageable getPageable( PageableDTO pageableDto ) {
        return PageRequest.of( pageableDto.getPageNumber(), pageableDto.getPageSize(),
                Sort.Direction.fromString( pageableDto.getSortDirection() ), pageableDto.getSortField() );
    }
}
