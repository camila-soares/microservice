package com.products.produtos.controllers;

import com.products.produtos.dtos.PageableDTO;
import com.products.produtos.dtos.ProdutosDTO;
import com.products.produtos.entity.Produtos;
import com.products.produtos.mapper.ProdutosMapper;
import com.products.produtos.services.ProdutosService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutosController {


    private final ProdutosService service;

    private final ProdutosMapper mapper;

    @PostMapping
    public ResponseEntity<Produtos> saveProdutos( @RequestBody Produtos produtosDTO ) {
        final Produtos produtos = service.save( produtosDTO );
        return new ResponseEntity( mapper.toProdutoDTO( produtos ), HttpStatus.CREATED );

    }

    @GetMapping
    public ResponseEntity findAllClient ( @RequestParam( required = false) String nome, PageableDTO pageableDTO ) {

        Pageable pageable = mapper.getPageable( pageableDTO );

        Page< Produtos > pageClient = service.findAllUser( nome, pageable );
        return new ResponseEntity( pageClient.getContent(), HttpStatus.OK ) ;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity< ProdutosDTO > findProdutoById( @PathVariable Long id ) {
        Produtos produtos = service.findProdutoById( id );
        ProdutosDTO produtosDTO = mapper.toProdutoDTO( produtos );
        return new ResponseEntity( produtosDTO, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public void deleteClient ( @PathVariable Long id) {
        service.deleteProduto( id );
    }


    @PutMapping("/{id}")
    public void updateCliente( @PathVariable Long id, @RequestBody ProdutosDTO produtosDTO ) {
        service.updateProduto( id, produtosDTO );

    }

}

