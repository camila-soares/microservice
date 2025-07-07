package com.microservice.pedido.model;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@Table(name = "produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "estoque", nullable = false)
    private Integer estoque;
    @Column(name = "preco", nullable = true)
    private Double price;
}
