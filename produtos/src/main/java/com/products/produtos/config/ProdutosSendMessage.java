package com.products.produtos.config;


import com.products.produtos.entity.Produtos;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProdutosSendMessage {

    public static String EXCHANGE_NAME ="produtos.exchange";

    public final RabbitTemplate rabbitTemplate;


    public void sendMessage( Produtos produto ) {
        System.out.println("Enviando mensagem");
        try{
           // String json = new ObjectMapper().w(produto, );
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, "", produto);
        } catch (RuntimeException e) {
            e.getMessage();
        }

    }
}
