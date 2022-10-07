package com.bookstore.orderservice.message;

import com.bookstore.orderservice.service.CartItemService;
import com.bookstore.orderservice.utils.StringConstant;
import com.bookstore.orderservice.vo.request.CartItemRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class KafkaConsumer {
    CartItemService cartItemService;

    @Autowired
    public KafkaConsumer(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @KafkaListener(topics = "add-product-to-cart")
    public void addProductToCart(String kafkaMessage) {
        log.info("Kafka Message : " + kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<>() {
            });
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        CartItemRequest cartItemRequest = CartItemRequest.builder()
                .productId((String) map.get("productId"))
                .quantity(Integer.parseInt((String) map.get("quantity")))
                .userId((String) map.get("userId"))
                .build();

        cartItemService.addCartItem(cartItemRequest);
    }

}