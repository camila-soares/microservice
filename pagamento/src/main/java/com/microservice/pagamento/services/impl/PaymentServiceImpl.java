package com.microservice.pagamento.services.impl;

import com.microservice.commons.dtos.OrderDTO;
import com.microservice.pagamento.broker.PaymentOut;
import com.microservice.pagamento.entity.Order;
import com.microservice.pagamento.mapper.OrderMapper;
import com.microservice.pagamento.repositories.PedidoRepository;
import com.microservice.pagamento.services.PaymentService;
import com.microservice.pagamento.utils.ConstantsAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {


   private final PaymentOut paymentOut;
    private final PedidoRepository pedidoRepository;


    @Override
    public void processaPedidoConfirmado(OrderDTO order) {
        Order orderEntity = OrderMapper.toOrderDTO(order);

        try {
           MediaType mediaType = MediaType.parse("application/json");
            OkHttpClient client = new OkHttpClient();
            String content = getString(order);
            RequestBody body = RequestBody.create(mediaType, content);
            assert mediaType != null;
            Request request = new Request.Builder()
                    .url(ConstantsAPI.BASE_URL)
                    .post(body)
                    .addHeader("accept", mediaType.toString())
                    .addHeader("MerchantId", ConstantsAPI.MERCHANTID)
                    .addHeader("MerchantKey", ConstantsAPI.MERCHANTKEY)
                    .addHeader("Content-Type", mediaType.toString())
                    .build();

            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                paymentOut.pagamentoAutorizado(order);
                pedidoRepository.save(orderEntity);
                log.info("[{}] Pagamento autorizado com Sucesso", order.getId());
            }else {
                paymentOut.pagamentoNegado(order);
                log.info("[{}] Pagamento Negado", order.getId());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    private static String getString(OrderDTO order) {
        return "{\"Customer\":{\"Address\":{\"Street\":\""+ order.getCustomer().getAddress().getStreet()+"\",\"Number\":\""+ order.getCustomer().getAddress().getNumber()+"\",\"Complement\":\""+ order.getCustomer().getAddress().getComplement()+"\",\"ZipCode\":\""+ order.getCustomer().getAddress().getZipCode()+"\",\"City\":\""+ order.getCustomer().getAddress().getCity()+"\",\"State\":\""+ order.getCustomer().getAddress().getState()+"\",\"Country\":\""+ order.getCustomer().getAddress().getCountry()+"\"},\"DeliveryAddress\":{\"Street\":\""+ order.getCustomer().getAddress().getStreet()+"\",\"Number\":\""+ order.getCustomer().getAddress().getNumber()+"\",\"Complement\":\""+ order.getCustomer().getAddress().getComplement()+"\",\"ZipCode\":\""+ order.getCustomer().getAddress().getZipCode()+"\",\"City\":\""+ order.getCustomer().getAddress().getCity()+"\",\"State\":\""+ order.getCustomer().getAddress().getState()+"\",\"Country\":\""+ order.getCustomer().getAddress().getCountry()+"\"},\"Billing\":{\"Street\":\""+ order.getCustomer().getAddress().getStreet()+"\",\"Number\":\""+ order.getCustomer().getAddress().getNumber()+"\",\"Complement\":\""+ order.getCustomer().getAddress().getComplement()+"\",\"Neighborhood\":\""+ order.getCustomer().getAddress().getNeighborhood()+"\",\"City\":\""+ order.getCustomer().getAddress().getCity()+"\",\"State\":\""+ order.getCustomer().getAddress().getState()+"\",\"Country\":\""+ order.getCustomer().getAddress().getCountry()+"\",\"ZipCode\":\""+ order.getCustomer().getAddress().getZipCode()+"\"},\"Name\":\""+ order.getCustomer().getName()+"\",\"Email\":\""+ order.getCustomer().getEmail()+"\",\"Birthdate\":\""+ order.getCustomer().getBirthdate()+"\"},\"Payment\":{\"Type\":\""+ order.getPayment().getType()+"\",\"AirlineData\":{\"TicketNumber\":\"\"},\"CreditCard\":{\"CardOnFile\":{\"Usage\":\""+ order.getPayment().getCreditCard().cardOnFile.getUsage()+"\",\"Reason\":\""+ order.getPayment().getCreditCard().cardOnFile.getReason()+"\"},\"CardNumber\":\""+ order.getPayment().getCreditCard().getCardNumber()+"\",\"Holder\":\""+ order.getPayment().getCreditCard().getHolder()+"\",\"ExpirationDate\":\""+ order.getPayment().getCreditCard().getExpirationDate()+"\",\"SecurityCode\":\""+ order.getPayment().getCreditCard().getSecurityCode()+"\",\"SaveCard\":\""+ order.getPayment().getCreditCard().getSaveCard()+"\",\"Brand\":\""+ order.getPayment().getCreditCard().getBrand().name()+"\"},\"Currency\":\""+ order.getPayment().getCurrency()+"\",\"Country\":\""+ order.getPayment().getCountry()+"\",\"ServiceTaxAmount\":"+ order.getPayment().getServiceTaxAmount()+",\"Installments\":"+ order.getPayment().getInstallments()+",\"Interest\":\""+ order.getPayment().getInterest()+"\",\"Capture\":"+ order.getPayment().getCapture()+",\"Authenticate\":\""+ order.getPayment().getAuthenticate()+"\",\"Recurrent\":\""+ order.getPayment().getRecurrent()+"\",\"SoftDescriptor\":\""+ order.getPayment().getSoftDescriptor()+"\",\"Tip\":"+ order.getPayment().getTip()+",\"IsCryptoCurrencyNegotiation\":"+ order.getPayment().getIsCryptoCurrencyNegotiation()+",\"Amount\":"+ order.getPayment().getAmount()+"},\"MerchantOrderId\":\""+ order.getMerchantOrderId()+"\"}";
    }

}
