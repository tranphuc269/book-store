package com.bookstore.catalogservice.service;

import javax.validation.Valid;

import com.bookstore.catalogservice.dao.CategoryDAO;
import com.bookstore.catalogservice.vo.request.CreateCategoryRequest;
import com.bookstore.catalogservice.vo.request.UpdateCategoryRequest;
import com.bookstore.catalogservice.vo.resonse.CategoryResponse;
import org.springframework.data.domain.Page;

import java.util.List;


public interface CategoryService {

  String createCategory(@Valid CreateCategoryRequest createCategoryRequest);

  CategoryResponse getCategory(String categoryId);

  CategoryResponse deleteCategory(String categoryId);

  CategoryResponse updateCategory(UpdateCategoryRequest updateCategoryRequest);

  List<CategoryResponse> getCategories(String sort, Integer page, Integer size);
}