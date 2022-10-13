package com.bookstore.catalogservice.vo.resonse.producer;


import com.bookstore.catalogservice.vo.resonse.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@RedisHash
@NoArgsConstructor
@AllArgsConstructor
public class ProducerResponse implements Serializable {

    private static final long serialVersionUID = 123456654323L;

    private String producerId;
    private String producerName;
    private String description;
    private String img;
}
