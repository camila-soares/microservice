package com.microservice.pedido.model;


import com.microservice.commons.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {


    @Id
    private String id = UUID.randomUUID().toString();

    private Double totalAmount;

    @Column(name = "status", length = 255)
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.received;

    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    @ElementCollection(fetch = FetchType.EAGER)
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
                .mapToDouble(v -> v.getPrice() * v.getCount()).sum();

    }
}
