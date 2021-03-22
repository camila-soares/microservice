package com.microservice.pagamento.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "produto_venda")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoVenda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(name = "id_produto", nullable = false, length = 10 )
    private Long idProduto;

    @ManyToOne
    @JoinColumn( name = "id_venda")
    private Venda venda;

    @Column(name = "quantidade", nullable = false, length = 10)
    private Integer quantidade;


}
