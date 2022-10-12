package com.bookstore.catalogservice.vo.resonse;

import com.bookstore.catalogservice.dao.CategoryDAO;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


@Data
@Builder
@RedisHash()
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoriesPagedResponse implements Serializable {
    
    Page<CategoryResponse> data;
    Map<String, String> _links = new HashMap<>();
    
}