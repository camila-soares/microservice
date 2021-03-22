package com.products.produtos.specification;

import com.products.produtos.entity.Produtos;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

@UtilityClass
public class ProdutosSpecification {

    public Specification< Produtos > nome( String nome ) {
        return ( root, criteriaQuery, criteriaBuilder ) -> {
            if( Objects.isNull( nome ) ) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal( root.get( "nome" ), nome );
        };
    }
}
