package com.microservice.pagamento.mapper;

import com.microservice.commons.dtos.*;

import com.microservice.pagamento.entity.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class OrderMapper {


    public static Order toOrderDTO(OrderDTO order){
        Order orderDTO = new Order();
        List<Item> items = new ArrayList<>();
        for (ItemDto dto : order.getItems()){
            Item item = new Item();
            item.setCount(dto.getCount());
            item.setProductId(dto.getProductId());
            item.setPrice(dto.getPrice());
            items.add(item);
        }
        CreditCard creditCard = getCreditcard(order);
        Payment paymentDTO = getPaymentDTO(order);
        orderDTO.setId(order.getId());
        orderDTO.setItems(items);
        paymentDTO.setCreditCard(creditCard);
        orderDTO.setCustomer(orderToCustomerDTO(order));
        orderDTO.setPayment(paymentDTO);
        orderDTO.setMerchantOrderId(order.getMerchantOrderId());
        return orderDTO;
    }

    private static Payment getPaymentDTO(OrderDTO order) {
        getCreditcard(order);

        return getPayment(order);

    }

    private static CreditCard getCreditcard(OrderDTO order) {
        CreditCard creditCard = new CreditCard();
        CardOnFile cardOnFile = new CardOnFile();
        cardOnFile.setReason(order.getPayment().getCreditCard().cardOnFile.getReason());
        cardOnFile.setUsage(order.getPayment().getCreditCard().cardOnFile.getUsage());
        creditCard.setCardOnFile(cardOnFile);
        creditCard.setCardNumber(order.getPayment().getCreditCard().getCardNumber());
        creditCard.setHolder(order.getPayment().getCreditCard().getHolder());
        creditCard.setSecurityCode(order.getPayment().getCreditCard().getSecurityCode());
        creditCard.setExpirationDate(order.getPayment().getCreditCard().getExpirationDate());
        creditCard.setSaveCard(order.getPayment().getCreditCard().getSaveCard());
        creditCard.setBrand(order.getPayment().getCreditCard().getBrand().getDescription());
        return creditCard;
    }

    @NotNull
    private static Payment getPayment(OrderDTO order) {
        Payment paymentDTO = new Payment();
        paymentDTO.setType(order.getPayment().getType());
       // paymentDTO.setCreditCard(creditCard);
        paymentDTO.setCurrency(order.getPayment().getCurrency());
        paymentDTO.setCountry(order.getCustomer().getAddress().getCountry());
        paymentDTO.setServiceTaxAmount(order.getPayment().getServiceTaxAmount());
        paymentDTO.setInstallments(order.getPayment().getInstallments());
        paymentDTO.setInterest(order.getPayment().getInterest());
        paymentDTO.setCapture(order.getPayment().getCapture());
        paymentDTO.setSoftDescriptor(order.getPayment().getSoftDescriptor());
        paymentDTO.setTip(order.getPayment().getTip());
        paymentDTO.setIsCryptoCurrencyNegotiation(order.getPayment().getIsCryptoCurrencyNegotiation());
        paymentDTO.setAmount(order.getPayment().getAmount());
        return paymentDTO;
    }

    public static Customer orderToCustomerDTO(OrderDTO order){
        Customer Customer = new Customer();
        Address address = getAddress(order);

        Customer.setIdCustomer(order.getCustomer().getIdCustomer());
        Customer.setName(order.getCustomer().getName());
        Customer.setEmail(order.getCustomer().getEmail());
        Customer.setBirthdate(order.getCustomer().getBirthdate());
        Customer.setAddress(address);
        return Customer;
    }

    @NotNull
    private static Address getAddress(OrderDTO order) {
        Address address = new Address();
        address.setStreet(order.getCustomer().getAddress().getStreet());
        address.setCity(order.getCustomer().getAddress().getCity());
        address.setState(order.getCustomer().getAddress().getState());
        address.setZipCode(order.getCustomer().getAddress().getZipCode());
        address.setNumber(order.getCustomer().getAddress().getNumber());
        address.setComplement(order.getCustomer().getAddress().getComplement());
        address.setNeighborhood(order.getCustomer().getAddress().getNeighborhood());
        return address;
    }

}
