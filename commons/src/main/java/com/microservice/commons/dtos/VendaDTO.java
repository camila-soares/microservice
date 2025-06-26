package com.microservice.commons.dtos;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendaDTO  {

    private Long id;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date data;
    private List<ProdutosVendaDTO> produtos;
    private Double valorTotal;
}
