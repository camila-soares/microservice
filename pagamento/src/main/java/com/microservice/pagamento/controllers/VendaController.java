package com.microservice.pagamento.controllers;

import com.microservice.pagamento.dtos.PageableDTO;
import com.microservice.pagamento.dtos.VendaDTO;
import com.microservice.pagamento.entity.Venda;
import com.microservice.pagamento.mapper.VendaMapper;
import com.microservice.pagamento.services.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    public VendaDTO saveProdutos( @RequestBody @Validated VendaDTO vendaDTO ) {
        vendaService.save( vendaDTO );
        return vendaDTO;
    }

    @GetMapping
    public ResponseEntity findAllClient ( @RequestParam( required = false)  PageableDTO pageableDTO ) {

        Pageable pageable = VendaMapper.getPageable( pageableDTO );

        Page< Venda > pageClient = vendaService.findAll( pageable );
        return new ResponseEntity( pageClient.getContent(), HttpStatus.OK ) ;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity< VendaDTO > findProdutoById( @PathVariable Long id ) {
        Venda venda = vendaService.findProdutoById( id );
        VendaDTO produtosDTO = VendaMapper.toProdutoDTO( venda );
        return new ResponseEntity( produtosDTO, HttpStatus.OK);

    }
}
