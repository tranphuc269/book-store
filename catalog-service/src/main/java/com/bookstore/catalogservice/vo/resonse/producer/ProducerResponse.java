package com.bookstore.catalogservice.vo.resonse.producer;


import com.bookstore.catalogservice.vo.resonse.ProductResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProducerResponse {
    private String producerId;
    private String producerName;
    private String description;
    private String img;
}
