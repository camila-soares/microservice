package com.microservice.commons.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutosVendaDTO {

    //private Long id;
    private Long idProduto;
    private Integer quantidade;
}
