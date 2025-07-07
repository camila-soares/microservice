package com.microservice.fulfillments.mapper;


import com.microservice.commons.dtos.FulfillmentDto;
import com.microservice.fulfillments.model.Fulfillment;
import org.springframework.stereotype.Component;


@Component
public class FulfillmentMapper {

     public static   FulfillmentDto toDto(Fulfillment fulfillment){
       return FulfillmentDto.builder().id(fulfillment.getId())
                .packagedAt(fulfillment.getPackagedAt())
                .orderId(fulfillment.getOrderId())
                .build();
    }
}
