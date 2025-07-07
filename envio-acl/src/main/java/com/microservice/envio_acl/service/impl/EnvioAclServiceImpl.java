package com.microservice.envio_acl.service.impl;


import com.microservice.commons.dtos.OrderDTO;
import com.microservice.commons.dtos.OrderDeliveryDto;
import com.microservice.envio_acl.broker.EnvioAclOutput;
import com.microservice.envio_acl.client.DeliverySystemHttpClient;
import com.microservice.envio_acl.client.dto.DeliveryRequestDto;
import com.microservice.envio_acl.service.EnvioAclService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnvioAclServiceImpl implements EnvioAclService {

    private final EnvioAclOutput output;
    private final DeliverySystemHttpClient httpClient;

    @Override
    public void processarPedidoEnvioSolicitado(OrderDTO order) {
        httpClient.requestOrderDelivery(DeliveryRequestDto.builder()
                .orderId(order.getId())
                .customer(order.getCustomer())
                .build());

        output.pedidoEnvioConfirmado(order);

    }

    @Override
    public void processarPedidoEntregue(OrderDeliveryDto delivery) {
            output.pedidoEntregue(delivery);
    }
}
