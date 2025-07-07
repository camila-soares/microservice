package com.microservice.envio_acl.service;

import com.microservice.commons.dtos.OrderDTO;
import com.microservice.commons.dtos.OrderDeliveryDto;

public interface EnvioAclService {

    void processarPedidoEnvioSolicitado(OrderDTO order);

    void processarPedidoEntregue(OrderDeliveryDto delivery);
}
