package com.bookstore.catalogservice.vo.resonse;

import com.bookstore.catalogservice.dao.ProductCategoryDAO;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;


@Data
public class ProductCategoriesPagedResponse {
    
    Page<ProductCategoryDAO> page;
    Map<String, String> _links = new HashMap<>();
    
}