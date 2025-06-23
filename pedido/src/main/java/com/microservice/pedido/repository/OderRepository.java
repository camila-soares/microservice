package com.microservice.pedido.repository;


import com.microservice.pedido.dto.enums.OrderStatus;
import com.microservice.pedido.model.Order;
import com.microservice.pedido.model.Payment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface OderRepository extends JpaRepository<Order, String> {

    @Modifying
    @Query("update Order o set o.reserved = true where o.id = ?1")
    Order reserve(String orderId);

    @Modifying
    @Query("update Order o set o.qualified = true where o.id = ?1")
    Order qualify(String orderId);

    @Modifying
    @Query("update Order o set o.status = ?1 where o.id = ?2")
    Order updateByStatus(OrderStatus status, String orderId);



    @Query("update Order set payment = ?2 where id = ?1")
    Order updatePagameto(String orderId, Payment payment);
}
