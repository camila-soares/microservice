package com.microservice.pagamento.repositories;

import com.microservice.pagamento.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
public interface VendaRepository extends JpaRepository< Venda, Long > {

   // Optional<Venda> findProdutoVendaIdProduto( Long idProduto);
}
