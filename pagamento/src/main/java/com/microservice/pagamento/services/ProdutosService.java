package com.microservice.pagamento.services;

import com.microservice.pagamento.dtos.ProdutosDTO;
import com.microservice.pagamento.entity.Pedido;
import com.microservice.pagamento.exception.ResourceNotFoundException;
import com.microservice.pagamento.mapper.ProdutosMapper;
import com.microservice.pagamento.repositories.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProdutosService {


    private final PedidoRepository pedidoRepository;


    public Pedido save(Pedido pedido ) {
        return pedidoRepository.save( pedido );

    }

    private ProdutosDTO convertToProdutoDTO(Pedido pedido ) {
        return ProdutosMapper.toProdutoDTO( pedido );
    }

    public Pedido findProdutoById( Long id ) {
        return pedidoRepository.findById(String.valueOf(id))
                .orElseThrow( () -> new ResourceNotFoundException( "Produto nao encontrado" ) );
    }
}
