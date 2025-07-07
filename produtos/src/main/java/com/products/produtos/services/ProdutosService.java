package com.products.produtos.services;

import com.microservice.commons.dtos.OrderDTO;
import com.microservice.commons.dtos.ProdutosDTO;
import com.products.produtos.broker.ProdutoOutput;
import com.products.produtos.entity.Produtos;
import com.products.produtos.exception.UnavailableItemsException;
import com.products.produtos.filter.ProdutoFilter;
import com.products.produtos.mapper.ProdutosMapper;
import com.products.produtos.repositories.ProdutosRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProdutosService {

    private final ProdutoOutput output;
    private final ProdutosRepository repository;

    private final ProdutosMapper mapper;

    public Produtos save(Produtos produtosDTO) {
        Produtos produtos;
        produtos = repository.save(produtosDTO );
       // produtosSendMessage.sendMessage( produtosDTO );
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

    public Produtos findProdutoById( String id ) {
        return repository.findById( id )
                .orElseThrow( () -> new ResponseStatusException( HttpStatus.NOT_FOUND ) );
    }

    public void updateProduto( String id, ProdutosDTO produtosDTO ) {

        repository.findById( id ).map( produto -> {
            produto.setNome( produtosDTO.getName() );
            produto.setEstoque( produtosDTO.getEstoque() );
            produto.setPrice( produtosDTO.getPreco() );
            return repository.save( produto );
        } ).orElseThrow( () -> new ResponseStatusException( HttpStatus.NO_CONTENT ) );
    }

    public void deleteProduto( String id ) {
        repository.findById( id ).map( client -> {
            repository.delete( client );
            return Void.TYPE;
        } ).orElseThrow( () -> new ResponseStatusException( HttpStatus.NOT_FOUND ) );
    }

    public void processarCriacaoDePedido(OrderDTO order) {
        try {
            order.getItems().forEach(item -> {
                Optional<Produtos> produtos = Optional.ofNullable(repository
                        .findById(item.getProductId())
                        .orElseThrow(UnavailableItemsException::new));

                produtos.get().sell(item.getCount());
                repository.save(produtos.get());
            });
            output.pedidoReservado(order);
            log.info("[{}] Reserva do pedido concluida", order.getId());
        } catch (UnavailableItemsException e) {
            output.pedidoRecusado(order);
            log.info("[{}] Reserva do pedido Recusada", order.getId());
        }
    }
}
