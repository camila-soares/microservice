package com.microservice.pagamento.dtos;

import com.microservice.pagamento.entity.Venda;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutosVendaDTO {

    private Long id;
    private Long idProduto;
    private Integer quantidade;
}
