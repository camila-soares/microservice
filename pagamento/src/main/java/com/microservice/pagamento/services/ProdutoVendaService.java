package com.microservice.pagamento.services;

import com.microservice.pagamento.repositories.ProdutoVendaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProdutoVendaService {


    private final ProdutoVendaRepository produtoVendaRepository;
}
