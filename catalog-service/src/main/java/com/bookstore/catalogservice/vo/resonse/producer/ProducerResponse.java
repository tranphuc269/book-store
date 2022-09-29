package com.bookstore.catalogservice.vo.resonse.producer;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProducerResponse {
    private String producerId;
    private String producerName;
    private String description;
    private String img;
}
