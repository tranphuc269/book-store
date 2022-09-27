package com.bookstore.catalogservice.model;

import com.bookstore.catalogservice.dao.ProductDAO;
import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;



@Data
public class ProductResource extends EntityModel<ProductDAO> {
    
    private Pageable pageable;
    
    public ProductResource(ProductDAO content, Link... links) {
        EntityModel.of(content, links);
    }
}