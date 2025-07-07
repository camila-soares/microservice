package com.microservice.pagamento.repositories;

import com.microservice.pagamento.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Order, String > {


}
