package com.microservice.pagamento.entity;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "venda")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Venda implements Serializable {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data", length = 20)
    private Date data;

    @OneToMany(fetch =  FetchType.LAZY, mappedBy = "venda", cascade = CascadeType.REFRESH)
    private List<ProdutoVenda> produtos;

    @Column(name = "valor_total", nullable = false, length = 20)
    private Double valorTotal;

    public List< ProdutoVenda > getProdutoVenda(){
        return Objects.nonNull( produtos ) ? produtos : new ArrayList<>();
    }

    private String code;
}
