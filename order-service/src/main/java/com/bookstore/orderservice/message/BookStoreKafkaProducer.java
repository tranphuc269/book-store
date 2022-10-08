package com.bookstore.orderservice.message;

import com.bookstore.orderservice.vo.request.feign.CreatePaymentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookStoreKafkaProducer {
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public BookStoreKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(
        String topic,
        CreatePaymentRequest createPaymentRequest
    ) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString  = "";

        try {
            jsonInString = mapper.writeValueAsString(createPaymentRequest);
        } catch(JsonProcessingException ex) {
            ex.printStackTrace();
        }
        System.out.println("Send payment to kafka with payload : " + jsonInString);
        kafkaTemplate.send(topic, jsonInString);
    }
}