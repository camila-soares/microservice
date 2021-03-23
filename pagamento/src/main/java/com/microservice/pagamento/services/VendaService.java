package com.microservice.pagamento.services;

import com.microservice.pagamento.dtos.VendaDTO;
import com.microservice.pagamento.entity.ProdutoVenda;
import com.microservice.pagamento.entity.Venda;
import com.microservice.pagamento.exception.ResourceNotFoundException;
import com.microservice.pagamento.mapper.ProdutosVendaMapper;
import com.microservice.pagamento.mapper.VendaMapper;
import com.microservice.pagamento.repositories.ProdutoVendaRepository;
import com.microservice.pagamento.repositories.ProdutosRepository;
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
    @Autowired
    private ProdutosRepository produtosRepository;

    public VendaDTO save( VendaDTO vendaDTO) {
        Venda venda = vendaRepository.save(VendaMapper.createEntity(vendaDTO));

        List<ProdutoVenda> produtosSalvos =  new ArrayList<>();
        vendaDTO.getProdutos().forEach(p -> {
            ProdutoVenda pv = ProdutosVendaMapper.create(p);
            pv.setVenda(venda);
            produtosSalvos.add(produtoVendaRepository.save(pv));
        });
        venda.setProdutos(produtosSalvos);

        return VendaMapper.create(venda);
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
