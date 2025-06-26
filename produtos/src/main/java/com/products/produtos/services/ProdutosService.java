package com.products.produtos.services;

import com.microservice.commons.dtos.ProdutosDTO;
import com.products.produtos.config.ProdutosSendMessage;
import com.products.produtos.entity.Produtos;
import com.products.produtos.filter.ProdutoFilter;
import com.products.produtos.mapper.ProdutosMapper;
import com.products.produtos.repositories.ProdutosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProdutosService {


    private final ProdutosRepository repository;

    private final ProdutosMapper mapper;
    private final ProdutosSendMessage produtosSendMessage;

    public Produtos save(Produtos produtosDTO) {
        Produtos produtos;
        produtos = repository.save(produtosDTO );
        produtosSendMessage.sendMessage( produtosDTO );
        return produtos;

    }

    public Page< Produtos > findAllUser( String nome, Pageable pageable ) {
        Page < Produtos > produtos = Optional.of( repository.getAll( ProdutoFilter.of( ProdutosMapper.userDetailsDto( nome ) ), pageable ) )
                .orElse( null );
        return new PageImpl( produtos.map( f -> mapper.toProdutoDTO( f ) ).getContent(),
                pageable, produtos.getTotalElements() );
    }

    private ProdutosDTO convertToProdutoDTO(Produtos produtos){
        return mapper.toProdutoDTO( produtos );
    }

    public Produtos findProdutoById( Long id ) {
        return repository.findById( id )
                .orElseThrow( () -> new ResponseStatusException( HttpStatus.NOT_FOUND ) );
    }

    public void updateProduto( Long id, ProdutosDTO produtosDTO ) {

        repository.findById( id ).map( produto -> {
            produto.setNome( produtosDTO.getName() );
            produto.setEstoque( produtosDTO.getEstoque() );
            produto.setPrice( produtosDTO.getPreco() );
            return repository.save( produto );
        } ).orElseThrow( () -> new ResponseStatusException( HttpStatus.NO_CONTENT ) );
    }

    public void deleteProduto( Long id ) {
        repository.findById( id ).map( client -> {
            repository.delete( client );
            return Void.TYPE;
        } ).orElseThrow( () -> new ResponseStatusException( HttpStatus.NOT_FOUND ) );
    }
}
