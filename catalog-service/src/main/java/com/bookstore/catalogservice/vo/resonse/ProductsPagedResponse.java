package com.bookstore.catalogservice.vo.resonse;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;


@Data
public class ProductsPagedResponse {

    Page<ProductResponse> page;
    Map<String, String> _links = new HashMap<>();
    
}