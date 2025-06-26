package com.microservice.pagamento.mapper;


import com.microservice.commons.dtos.PageableDTO;
import com.microservice.commons.dtos.VendaDTO;
import com.microservice.pagamento.entity.Venda;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class VendaMapper {

    public static VendaDTO vendaDtoToEntity(Venda venda ) {
        return VendaDTO.builder()
                .id( venda.getId() )
                .data( venda.getData() )
                .valorTotal( venda.getValorTotal() )
                .build();
    }

    public static VendaDTO userEntityToDto( Venda venda ) {
        return VendaDTO
                .builder()
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

    public static Venda createEntity(VendaDTO vendaDTO) {
        return new ModelMapper().map(vendaDTO, Venda.class);
    }

    public static VendaDTO create(Venda venda) {
        return new ModelMapper().map(venda, VendaDTO.class);
    }

    public static Pageable getPageable( PageableDTO pageableDto ) {
        return PageRequest.of( pageableDto.getPageNumber(), pageableDto.getPageSize(),
                Sort.Direction.fromString( pageableDto.getSortDirection() ), pageableDto.getSortField() );
    }
}
