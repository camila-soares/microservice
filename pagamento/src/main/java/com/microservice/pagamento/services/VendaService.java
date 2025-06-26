package com.microservice.pagamento.services;

import com.microservice.commons.dtos.VendaDTO;
import com.microservice.pagamento.config.PedidosListnerMessage;
import com.microservice.pagamento.entity.ProdutoVenda;
import com.microservice.pagamento.entity.Venda;
import com.microservice.pagamento.exception.ResourceNotFoundException;
import com.microservice.pagamento.mapper.ProdutosVendaMapper;
import com.microservice.pagamento.mapper.VendaMapper;
import com.microservice.pagamento.repositories.ProdutoVendaRepository;
import com.microservice.pagamento.repositories.PedidoRepository;
import com.microservice.pagamento.repositories.VendaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class VendaService {


    private final VendaRepository vendaRepository;
    @Autowired
    private ProdutoVendaRepository produtoVendaRepository;
    @Autowired
    private PedidoRepository produtosRepository;
    @Autowired
    PedidosListnerMessage listnerMessage;

    @Transactional
    public VendaDTO save(VendaDTO vendaDTO) {

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
        return vendaRepository.findAll(pageable);
    }

    private Venda convertToVendaDTO(VendaDTO venda){
        return VendaMapper.toProdutoEntity( venda );
    }

    public Venda findProdutoById( Long id ) {
        return vendaRepository.findById( id )
                .orElseThrow( () -> new ResourceNotFoundException( "Venda não encontrada") );
    }

}
