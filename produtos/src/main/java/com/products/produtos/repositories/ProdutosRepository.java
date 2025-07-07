package com.products.produtos.repositories;

import com.products.produtos.entity.Produtos;
import com.products.produtos.filter.ProdutoFilter;
import com.products.produtos.specification.ProdutosSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import static org.springframework.data.jpa.domain.Specification.where;

@Repository
public interface ProdutosRepository extends JpaRepository< Produtos, String >, JpaSpecificationExecutor< Produtos > {

    default Page< Produtos > getAll( ProdutoFilter filter, Pageable pageable ) {
        Specification spec = where( ProdutosSpecification.nome( filter.getNome() ) );
        return findAll( spec, pageable );
    }
}
