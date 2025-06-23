package com.products.produtos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table( name = "produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produtos implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id = UUID.randomUUID().toString();

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "estoque", nullable = false, length = 10)
    private Integer estoque;

    @Column(name = "preco", nullable = false)
    private Double price;
}
