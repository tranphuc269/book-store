package com.bookstore.paymentservice.message;

import com.bookstore.paymentservice.service.PaymentService;
import com.bookstore.paymentservice.utils.PaymentType;
import com.bookstore.paymentservice.vo.request.PaymentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class BookStoreKafkaConsumer {

    PaymentService paymentService;

    @Autowired
    public BookStoreKafkaConsumer(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @KafkaListener(topics = "kafka-payment")
    public void addProductToCart(String kafkaMessage) {
        log.info("Kafka Message from payment : " + kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<>() {

            });
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        String paymentType = (String) map.get("paymentType");
        if (paymentType.equals(PaymentType.COD.name())) {
            paymentService.paymentWithCOD(PaymentRequest
                    .builder()
                    .amount((Long) map.get("amount"))
                    .paymentType(PaymentType.COD)
                    .information((String) map.get("information"))
                    .orderId((String) map.get("orderId"))
                    .build());
        }else{
            paymentService.paymentWithCOD(PaymentRequest
                    .builder()
                    .amount((Long) map.get("amount"))
                    .paymentType(PaymentType.BANKING)
                    .information((String) map.get("information"))
                    .orderId((String) map.get("orderId"))
                    .build());
        }
    }
}
