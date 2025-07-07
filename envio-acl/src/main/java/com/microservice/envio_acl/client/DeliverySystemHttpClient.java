package com.microservice.envio_acl.client;

import com.microservice.envio_acl.client.dto.DeliveryRequestDto;
import com.microservice.envio_acl.client.dto.DeliveryResponseDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface DeliverySystemHttpClient {

    @PostExchange("/entregas")
    DeliveryResponseDto requestOrderDelivery(@RequestBody DeliveryRequestDto deliveryRequest);

}
