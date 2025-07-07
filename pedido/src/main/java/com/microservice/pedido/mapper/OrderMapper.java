package com.microservice.pedido.mapper;


import com.microservice.commons.dtos.*;
import com.microservice.commons.dtos.CreditCardDTO;
import com.microservice.pedido.model.*;
import com.microservice.pedido.model.CreditCard;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


@Data
public class OrderMapper {

    public static OrderDTO orderToOrderDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();
        List<ItemDto> itemDtoList = new ArrayList<>();
        for (Item item : order.getItems()){
            ItemDto dto = ItemDto.builder().count(item.getCount())
                    .productId(item.getProductId()).price(item.getPrice()).build();
            itemDtoList.add(dto);
        }

        CreditCardDTO creditCardDTO = new CreditCardDTO();
        CardOnFileDTO cardOnFile = new CardOnFileDTO();
        cardOnFile.setReason(order.getPayment().getCreditCard().cardOnFile.getReason());
        cardOnFile.setUsage(order.getPayment().getCreditCard().cardOnFile.getUsage());
        creditCardDTO.setCardOnFile(cardOnFile);
        creditCardDTO.setCardNumber(order.getPayment().getCreditCard().getCardNumber());
        creditCardDTO.setHolder(order.getPayment().getCreditCard().getHolder());
        creditCardDTO.setSecurityCode(order.getPayment().getCreditCard().getSecurityCode());
        creditCardDTO.setExpirationDate(order.getPayment().getCreditCard().getExpirationDate());
        creditCardDTO.setSaveCard(order.getPayment().getCreditCard().getSaveCard());
        creditCardDTO.setBrand(order.getPayment().getCreditCard().getBrand());
        PaymentDTO paymentDTO = getPaymentDTO(order, creditCardDTO);
        orderDTO.setId(order.getId());
        orderDTO.setItems(itemDtoList);
        orderDTO.setCustomer(orderToCustomerDTO(order));
        orderDTO.setPayment(paymentDTO);
        orderDTO.setMerchantOrderId(order.getMerchantOrderId());
        return orderDTO;
    }

    private static @NotNull PaymentDTO getPaymentDTO(Order order,
                                                     CreditCardDTO creditCardDTO) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setType(order.getPayment().getType());
        paymentDTO.setCreditCard(creditCardDTO);
        paymentDTO.setCurrency(order.getPayment().getCurrency());
        paymentDTO.setCountry(order.getCustomer().getAddress().getCountry());
        paymentDTO.setServiceTaxAmount(order.getPayment().getServiceTaxAmount());
        paymentDTO.setInstallments(order.getPayment().getInstallments());
        paymentDTO.setInterest(order.getPayment().getInterest());
        paymentDTO.setCapture(order.getPayment().getCapture());
        paymentDTO.setAuthenticate(order.getPayment().getAuthenticate());
        paymentDTO.setRecurrent(order.getPayment().getRecurrent());
        paymentDTO.setSoftDescriptor(order.getPayment().getSoftDescriptor());
        paymentDTO.setTip(order.getPayment().getTip());
        paymentDTO.setIsCryptoCurrencyNegotiation(order.getPayment().getIsCryptoCurrencyNegotiation());
        paymentDTO.setAmount(order.getPayment().getAmount());
        return paymentDTO;
    }

    public static Order orderCreationDTO(OrderCreationDTO dto){
        Order order = new Order();
       List<Item> items = new ArrayList<>();
        for (ItemDto item1 : dto.getItems()){
            Item item = new Item();
            item.setProductId(item1.getProductId());
            item.setCount(item1.getCount());
            item.setPrice(item1.getPrice());
            items.add(item);
        }
        order.setItems(items);
        order.setCustomer(customerDTOEntity(dto.getCustomer()));
        order.setPayment(OrderMapper.toPayment(dto.getPayment()));
        order.setMerchantOrderId(dto.getMerchantOrderId());
        return order;
    }

    public static CustomerDTO orderToCustomerDTO(Order order){
        CustomerDTO customerDTO = new CustomerDTO();
        AddressDto addressDto = new AddressDto(
               order.getCustomer().getAddress().getStreet(),
                order.getCustomer().getAddress().getNumber(),
                order.getCustomer().getAddress().getComplement(),
                order.getCustomer().getAddress().getZipCode(),
                order.getCustomer().getAddress().getCity(),
                order.getCustomer().getAddress().getState(),
                order.getCustomer().getAddress().getCountry(),
                order.getCustomer().getAddress().getNeighborhood()
        );
        customerDTO.setIdCustomer(order.getCustomer().getIdCustomer());
        customerDTO.setName(order.getCustomer().getName());
        customerDTO.setEmail(order.getCustomer().getEmail());
        customerDTO.setBirthdate(order.getCustomer().getBirthdate());
        customerDTO.setAddress(addressDto);
        return customerDTO;
    }

    public static Customer customerDTOEntity(CustomerDTO order){
        Address address = new Address(order.getAddress().getStreet(),
                order.getAddress().getNumber(), order.getAddress().getComplement(),
                order.getAddress().getZipCode(), order.getAddress().getCity(),
                order.getAddress().getState(), order.getAddress().getCountry(), order.getAddress().getNeighborhood());
        Customer customer = new Customer();
        customer.setIdCustomer(order.getIdCustomer());
        customer.setName(order.getName());
        customer.setEmail(order.getEmail());
        customer.setBirthdate(order.getBirthdate());
        customer.setAddress(address);
        return customer;
    }
//
//    public CreditCard orderToCreditCard(OrderDTO order){
//        CreditCard creditCard = new CreditCard();
////        CardOnFile cardOnFile = new CardOnFile();
//        creditCard.cardOnFile.setUsage(order.getPayment().getCreditCard().cardOnFile.getUsage());
//        creditCard.cardOnFile.setReason(order.getPayment().getCreditCard().cardOnFile.getReason());
//       creditCard.setCardOnFile(creditCard.getCardOnFile());
//       creditCard.setCardNumber(order.getPayment().getCreditCard().getCardNumber());
//       creditCard.setHolder(order.getPayment().getCreditCard().getHolder());
//       creditCard.setExpirationDate(order.getPayment().getCreditCard().getExpirationDate());
//       creditCard.setSecurityCode(order.getPayment().getCreditCard().getSecurityCode());
//       creditCard.setSaveCard(order.getPayment().getCreditCard().getSaveCard());
//       creditCard.setBrand(order.getPayment().getCreditCard().getBrand());
//        return creditCard;
//    }
//    public static CreditCard toCreditCard(CreditCardDTO creditCardDTO){
//        CreditCard creditCard = new CreditCard();
//        creditCard.setCardNumber(creditCardDTO.getCardNumber());
//        creditCard.setHolder(creditCardDTO.getHolder());
//        creditCard.setExpirationDate(creditCardDTO.getExpirationDate());
//        creditCard.setSecurityCode(creditCardDTO.getSecurityCode());
//        creditCard.setSaveCard(creditCardDTO.getSaveCard());
//        creditCard.setBrand(creditCardDTO.getBrand());
//        return creditCard;
//    }

    public static Payment toPayment(PaymentDTO dto){
        Payment payment = new Payment();
        CreditCard creditCard = getCreditCard(dto);
        payment.setType(dto.getType());
        payment.setCreditCard(creditCard);
        payment.setCurrency(dto.getCurrency());
        payment.setServiceTaxAmount(dto.getServiceTaxAmount());
        payment.setInstallments(dto.getInstallments());
        payment.setInterest(dto.getInterest());
        payment.setCapture(dto.getCapture());
        payment.setAuthenticate(dto.getAuthenticate());
        payment.setRecurrent(dto.getRecurrent());
        payment.setSoftDescriptor(dto.getSoftDescriptor());
        payment.setTip(dto.getTip());
        payment.setIsCryptoCurrencyNegotiation(dto.getIsCryptoCurrencyNegotiation());
        payment.setAmount(dto.getAmount());
        return payment;
    }

    private static @NotNull CreditCard getCreditCard(PaymentDTO dto) {
        CreditCard creditCard = new CreditCard();
        CardOnFile cardOnFile = new CardOnFile();
        cardOnFile.setUsage(dto.getCreditCard().cardOnFile.getUsage());
        cardOnFile.setReason(dto.getCreditCard().cardOnFile.getReason());
        creditCard.setCardOnFile(cardOnFile);
        creditCard.setCardNumber(dto.getCreditCard().getCardNumber());
        creditCard.setHolder(dto.getCreditCard().getHolder());
        creditCard.setExpirationDate(dto.getCreditCard().getExpirationDate());
        creditCard.setSecurityCode(dto.getCreditCard().getSecurityCode());
        creditCard.setSaveCard(dto.getCreditCard().getSaveCard());
        creditCard.setBrand(dto.getCreditCard().getBrand());
        return creditCard;
    }

//    public  PaymentDTO orderToPaymentDTO(Order order){
//        PaymentDTO paymentDTO = new PaymentDTO();
//        CreditCardDTO creditCardDTO = new CreditCardDTO();
//        CardOnFile cardOnFile = new CardOnFile();
//        cardOnFile.setUsage(order.getPayment().getCreditCard().cardOnFile.getUsage());
//        cardOnFile.setReason(order.getPayment().getCreditCard().cardOnFile.getReason());
//        creditCardDTO.setCardOnFile(cardOnFile);
//        creditCardDTO.setCardNumber(order.getPayment().getCreditCard().getCardNumber());
//        creditCardDTO.setHolder(order.getPayment().getCreditCard().getHolder());
//        creditCardDTO.setExpirationDate(order.getPayment().getCreditCard().getExpirationDate());
//        creditCardDTO.setSecurityCode(order.getPayment().getCreditCard().getSecurityCode());
//        creditCardDTO.setSaveCard(order.getPayment().getCreditCard().getSaveCard());
//        creditCardDTO.setBrand(order.getPayment().getCreditCard().getBrand());
//
//        paymentDTO.setType(order.getPayment().getType());
//        paymentDTO.setCreditCard(creditCardDTO);
//        paymentDTO.setCurrency(order.getPayment().getCurrency());
//        paymentDTO.setCountry(order.getCustomer().getAddress().getCountry());
//        paymentDTO.setServiceTaxAmount(order.getPayment().getServiceTaxAmount());
//        paymentDTO.setInstallments(order.getPayment().getInstallments());
//        paymentDTO.setInterest(order.getPayment().getInterest());
//        paymentDTO.setCapture(order.getPayment().getCapture());
//        paymentDTO.setSoftDescriptor(order.getPayment().getSoftDescriptor());
//        paymentDTO.setTip(order.getPayment().getTip());
//        paymentDTO.setIsCryptoCurrencyNegotiation(order.getPayment().getIsCryptoCurrencyNegotiation());
//        return paymentDTO;
//    }

//    public  CreditCardDTO orderToCreditCardDTO(Order order){
//        CardOnFile cardOnFile = new CardOnFile();
//        cardOnFile.setUsage(order.getPayment().getCreditCard().cardOnFile.getUsage());
//        cardOnFile.setReason(order.getPayment().getCreditCard().cardOnFile.getReason());
//        CreditCardDTO creditCardDTO = new CreditCardDTO();
//        creditCardDTO.setCardOnFile(cardOnFile);
//        creditCardDTO.setCardNumber(order.getPayment().getCreditCard().getCardNumber());
//        creditCardDTO.setExpirationDate(order.getPayment().getCreditCard().getExpirationDate());
//        creditCardDTO.setHolder(order.getPayment().getCreditCard().getHolder());
//        creditCardDTO.setSecurityCode(order.getPayment().getCreditCard().getSecurityCode());
//        creditCardDTO.setSaveCard(order.getPayment().getCreditCard().getSaveCard());
//        creditCardDTO.setBrand(order.getPayment().getCreditCard().getBrand());
//       return creditCardDTO;
//    }

}
