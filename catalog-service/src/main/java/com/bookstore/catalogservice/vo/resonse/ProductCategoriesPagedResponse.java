package com.bookstore.catalogservice.vo.resonse;

import com.bookstore.catalogservice.dao.CategoryDAO;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;


@Data
public class ProductCategoriesPagedResponse {
    
    Page<CategoryDAO> data;
    Map<String, String> _links = new HashMap<>();
    
}