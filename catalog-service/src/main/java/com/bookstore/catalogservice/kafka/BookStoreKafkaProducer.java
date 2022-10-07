package com.bookstore.catalogservice.kafka;

import com.bookstore.catalogservice.vo.request.CreateCartItem;
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

    public CreateCartItem send(
        String topic,
        CreateCartItem createCartItem
    ) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString  = "";

        try {
            jsonInString = mapper.writeValueAsString(createCartItem);
        } catch(JsonProcessingException ex) {
            ex.printStackTrace();
        }

        kafkaTemplate.send(topic, jsonInString);

        log.info("Kafka Producer sent data from the Catalog Service: " + createCartItem);

        return createCartItem;
    }
}