package com.microservice.pagamento.repositories;

import com.microservice.pagamento.entity.ProdutoVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoVendaRepository extends JpaRepository< ProdutoVenda, Long > {
}
