package com.bookstore.catalogservice.service.impl;

import com.bookstore.catalogservice.dao.CategoryDAO;
import com.bookstore.catalogservice.repository.CategoryRepository;
import com.bookstore.catalogservice.service.CategoryService;
import com.bookstore.catalogservice.vo.request.CreateCategoryRequest;
import com.bookstore.catalogservice.vo.request.UpdateCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public String createCategory(
            @Valid CreateCategoryRequest createCategoryRequest) {

        CategoryDAO categoryDAO = CategoryDAO.builder()
                .categoryName(createCategoryRequest.getCategoryName())
                .description(createCategoryRequest.getDescription())
                .imgUrl(createCategoryRequest.getImgUrl())
                .build();

        CategoryDAO savedCategory = categoryRepository.save(categoryDAO);
        return savedCategory.getCategoryId();
    }

    @Override
    public CategoryDAO getCategory(String categoryId) {
        Optional<CategoryDAO> categoryDAOOptional = categoryRepository.findById(categoryId);

        return categoryDAOOptional.orElseThrow(() -> new RuntimeException("Category doesn't exist!"));
    }

    @Override
    public void deleteCategory(String categoryId) {

        categoryRepository.deleteById(categoryId);

    }

    @Override
    public void updateCategory(UpdateCategoryRequest updateCategoryRequest) {

        //To check weather the category exist.
        CategoryDAO getCategory =
                this.getCategory(updateCategoryRequest.getCategoryId());

        CategoryDAO category = CategoryDAO.builder()
                .categoryId(updateCategoryRequest.getCategoryId())
                .categoryName(updateCategoryRequest.getCategoryName())
                .description(updateCategoryRequest.getDescription())
                .build();

        category.setCreatedAt(getCategory.getCreatedAt());

        categoryRepository.save(category);

    }

    @Override
    public Page<CategoryDAO> getCategories(String sort, Integer page, Integer size) {


        //set defaults
        if (page == null || page == 0) {
            page = 0;
            size = 20;
        }

        Pageable pageable;

        if (sort == null) {
            pageable = PageRequest.of(page, size);
        } else {
            Sort.Order order;

            try {
                String[] split = sort.split(",");

                Sort.Direction sortDirection = Sort.Direction.fromString(split[1]);
                order = new Sort.Order(sortDirection, split[0]).ignoreCase();
                pageable = PageRequest.of(page, size, Sort.by(order));

            } catch (Exception e) {
                throw new RuntimeException("Not a valid sort value, It should be 'fieldName,direction', example : 'category,asc");
            }

        }
        return categoryRepository.findAll(pageable);
    }
}
