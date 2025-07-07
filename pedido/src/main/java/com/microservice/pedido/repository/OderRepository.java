package com.microservice.pedido.repository;


import com.microservice.commons.enums.OrderStatus;
import com.microservice.pedido.model.Order;
import com.microservice.pedido.model.Payment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Repository
public interface OderRepository extends JpaRepository<Order, String> {


    List<Order> findByCustomerEmail(String email);

    @Modifying
    @Query("update Order o set o.reserved = true where o.id = ?1")
    void reserve(String orderId);

    @Modifying
    @Query("update Order o set o.qualified = true where o.id = :orderId")
    int qualify(@Param("orderId") String orderId);

    @Modifying
    @Query("update Order o set o.status = ?1, o.updatedAt = ?3 where o.id = ?2")
    void updateByStatus(OrderStatus status, String orderId, LocalDateTime updatedAt);



    @Transactional
    @Modifying
    @Query("update Order o set o.payment = :payment where o.id = :orderId ")
    void updatePagameto(@Param("orderId") String orderId,@Param("payment") Payment payment);
}
