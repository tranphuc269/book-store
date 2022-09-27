package com.bookstore.catalogservice.service;

import javax.validation.Valid;

import com.bookstore.catalogservice.dao.ProductCategoryDAO;
import com.bookstore.catalogservice.vo.request.CreateProductCategoryRequest;
import com.bookstore.catalogservice.vo.request.UpdateProductCategoryRequest;
import org.springframework.data.domain.Page;


public interface ProductCategoryService {

  String createProductCategory(@Valid CreateProductCategoryRequest createProductCategoryRequest);

  ProductCategoryDAO getProductCategory(String productCategoryId);

  void deleteProductCategory(String productCategoryId);

  void updateProductCategory(UpdateProductCategoryRequest updateProductCategoryRequest);

  Page<ProductCategoryDAO> getAllProductCategories(String sort, Integer page, Integer size);
}