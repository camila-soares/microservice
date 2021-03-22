package com.microservice.pagamento.services;

import com.microservice.pagamento.dtos.VendaDTO;
import com.microservice.pagamento.entity.ProdutoVenda;
import com.microservice.pagamento.entity.Venda;
import com.microservice.pagamento.exception.ResourceNotFoundException;
import com.microservice.pagamento.mapper.VendaMapper;
import com.microservice.pagamento.repositories.ProdutoVendaRepository;
import com.microservice.pagamento.repositories.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private ProdutoVendaRepository produtoVendaRepository;

    public VendaDTO save( VendaDTO vendaDTO) {
        Venda venda =  vendaRepository.save( VendaMapper.toProdutoEntity( vendaDTO ) );
        List< ProdutoVenda > produtosSalvo = new ArrayList<>();
        venda.getProdutos().forEach( p -> {
            ProdutoVenda pv = p;
            pv.setVenda( venda );
            produtosSalvo.add( produtoVendaRepository.save( pv ) );
        } );
        venda.setProdutos( produtosSalvo );
        return VendaMapper.toProdutoDTO( venda );
    }

    public Page<Venda> findAll( Pageable pageable) {
        var page = vendaRepository.findAll(pageable);
        return page;
    }

    private Venda convertToVendaDTO(VendaDTO venda){
        return VendaMapper.toProdutoEntity( venda );
    }

    public Venda findProdutoById( Long id ) {
        return vendaRepository.findById( id )
                .orElseThrow( () -> new ResourceNotFoundException( "Venda não encontrada") );
    }

}
