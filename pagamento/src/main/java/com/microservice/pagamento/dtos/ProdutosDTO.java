package com.microservice.pagamento.dtos;


import com.microservice.pagamento.dtos.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutosDTO {


    private String id;
    private BigDecimal totalAmount;
    private OrderStatus status = OrderStatus.received;


}
