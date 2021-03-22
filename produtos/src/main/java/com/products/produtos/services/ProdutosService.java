package com.products.produtos.services;

import com.products.produtos.config.ProdutosSendMessage;
import com.products.produtos.dtos.ProdutosDTO;
import com.products.produtos.entity.Produtos;
import com.products.produtos.filter.ProdutoFilter;
import com.products.produtos.mapper.ProdutosMapper;
import com.products.produtos.repositories.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Service
public class ProdutosService {

    @Autowired
    private ProdutosRepository repository;

    @Autowired
    private ProdutosSendMessage produtosSendMessage;

    public Produtos save(Produtos produtosDTO) {
        Produtos produtos;
        produtos = repository.save(produtosDTO );
        produtosSendMessage.sendMessage( produtosDTO );
        return produtos;

    }

    public Page< Produtos > findAllUser( String nome, Pageable pageable ) {
        Page < Produtos > produtos = Optional.of( repository.getAll( ProdutoFilter.of( ProdutosMapper.userDetailsDto( nome ) ), pageable ) )
                .orElse( null );
        return new PageImpl( produtos.map( f -> ProdutosMapper.toProdutoDTO( f ) ).getContent(),
                pageable, produtos.getTotalElements() );
    }

    private ProdutosDTO convertToProdutoDTO(Produtos produtos){
        return ProdutosMapper.toProdutoDTO( produtos );
    }

    public Produtos findProdutoById( Long id ) {
        return repository.findById( id )
                .orElseThrow( () -> new ResponseStatusException( HttpStatus.NOT_FOUND ) );
    }

    public void updateProduto( Long id, ProdutosDTO produtosDTO ) {

        repository.findById( id ).map( produto -> {
            produto.setNome( produtosDTO.getNome() );
            produto.setEstoque( produtosDTO.getEstoque() );
            produto.setPreco( produtosDTO.getPreco() );
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
