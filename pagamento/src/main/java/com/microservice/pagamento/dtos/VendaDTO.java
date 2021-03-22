package com.microservice.pagamento.dtos;

import com.microservice.pagamento.entity.ProdutoVenda;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendaDTO {

    private Long id;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date data;
    private String produtos;
    private Double valorTotal;
}
