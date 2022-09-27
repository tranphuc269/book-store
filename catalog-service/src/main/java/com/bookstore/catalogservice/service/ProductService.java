package com.bookstore.catalogservice.service;


import javax.validation.Valid;

import com.bookstore.catalogservice.dao.ProductDAO;
import com.bookstore.catalogservice.vo.request.CreateProductRequest;
import com.bookstore.catalogservice.vo.resonse.ProductResponse;
import com.bookstore.catalogservice.vo.resonse.UpdateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService {

  String createProduct(@Valid CreateProductRequest createProductRequest);

  ProductResponse getProduct(String productId);

  void deleteProduct(String productId);

  void updateProduct(UpdateProductRequest updateProductRequest);

  Page<ProductDAO> findAllProducts(Pageable pageable);

  Page<ProductResponse> getAllProducts(String sort, Integer page, Integer size);
}