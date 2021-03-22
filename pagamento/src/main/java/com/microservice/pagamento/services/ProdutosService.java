package com.microservice.pagamento.services;

import com.microservice.pagamento.dtos.ProdutosDTO;
import com.microservice.pagamento.entity.Produtos;
import com.microservice.pagamento.exception.ResourceNotFoundException;
import com.microservice.pagamento.mapper.ProdutosMapper;
import com.microservice.pagamento.repositories.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProdutosService {

    @Autowired
    private ProdutosRepository produtosRepository;


    public Produtos save( Produtos produtosDTO ) {
        return produtosRepository.save( produtosDTO );

    }

    private ProdutosDTO convertToProdutoDTO( Produtos produtos ) {
        return ProdutosMapper.toProdutoDTO( produtos );
    }

    public Produtos findProdutoById( Long id ) {
        return produtosRepository.findById( id )
                .orElseThrow( () -> new ResourceNotFoundException( "Produto nao encontrado" ) );
    }
}
