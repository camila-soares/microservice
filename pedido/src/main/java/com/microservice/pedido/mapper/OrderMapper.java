package com.microservice.pedido.mapper;

import com.microservice.pedido.dto.OrderCreationDTO;
import com.microservice.pedido.dto.OrderDTO;
import com.microservice.pedido.dto.PaymentDTO;
import com.microservice.pedido.model.Order;
import com.microservice.pedido.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO toDTO(Order order);

    @Mappings({
            @Mapping(source = "items", target = "items"),
            @Mapping(source = "customer", target = "customer"),
            @Mapping(source = "payment", target = "payment")
    })
    Order toEntity(OrderCreationDTO dto);

    Payment toPayment(PaymentDTO dto);
}
