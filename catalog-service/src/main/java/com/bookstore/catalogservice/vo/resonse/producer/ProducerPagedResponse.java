package com.bookstore.catalogservice.vo.resonse.producer;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class ProducerPagedResponse {
    Page<ProducerResponse> data;
    Map<String, String> _links;
}
