package com.microservice.pagamento.entity;

import com.microservice.pagamento.dtos.Customer;
import com.microservice.pagamento.dtos.Item;
import com.microservice.pagamento.dtos.Payment;
import com.microservice.pagamento.dtos.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido implements Serializable {

    @Id
    private String id = UUID.randomUUID().toString();

    private BigDecimal totalAmount;

    private OrderStatus status = OrderStatus.received;

    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    @ElementCollection
    private List<Item> items = new ArrayList<>();

    @Embedded
    private Customer customer;

    @Embedded
    private Payment payment;

    private boolean qualified;

    private boolean reserved;


    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    public void calcularValorTotal() {
        totalAmount = items.stream()
                .map(v -> v.getPrice().multiply(BigDecimal.valueOf(v.getCount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }
}
