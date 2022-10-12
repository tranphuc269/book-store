package com.bookstore.catalogservice.service;

import javax.validation.Valid;

import com.bookstore.catalogservice.dao.CategoryDAO;
import com.bookstore.catalogservice.vo.request.CreateCategoryRequest;
import com.bookstore.catalogservice.vo.request.UpdateCategoryRequest;
import org.springframework.data.domain.Page;


public interface CategoryService {

  String createCategory(@Valid CreateCategoryRequest createCategoryRequest);

  CategoryDAO getCategory(String categoryId);

  void deleteCategory(String categoryId);

  CategoryDAO updateCategory(UpdateCategoryRequest updateCategoryRequest);

  Page<CategoryDAO> getCategories(String sort, Integer page, Integer size);
}