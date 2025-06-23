package com.microservice.pagamento.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "id_venda")
    private Venda venda;

    @Column(name = "quantidade", nullable = false, length = 10)
    private Integer quantidade;


}
