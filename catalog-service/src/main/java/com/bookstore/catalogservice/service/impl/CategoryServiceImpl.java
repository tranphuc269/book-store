package com.bookstore.catalogservice.service.impl;

import com.bookstore.catalogservice.dao.CategoryDAO;
import com.bookstore.catalogservice.repository.CategoryRepository;
import com.bookstore.catalogservice.service.CategoryService;
import com.bookstore.catalogservice.vo.request.CreateCategoryRequest;
import com.bookstore.catalogservice.vo.request.UpdateCategoryRequest;
import com.bookstore.catalogservice.vo.resonse.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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
    public CategoryResponse getCategory(String categoryId) {
        System.out.println("categoryId : " + categoryId);
        Optional<CategoryDAO> categoryDAOOptional = categoryRepository.findById(categoryId);

        if (categoryDAOOptional.isEmpty()) {
            throw new RuntimeException("Category doesn't exist!");
        }
        return CategoryResponse
                .builder()
                .id(categoryDAOOptional.get().getCategoryId())
                .name(categoryDAOOptional.get().getCategoryName())
                .description(categoryDAOOptional.get().getDescription())
                .imgUrl(categoryDAOOptional.get().getImgUrl())
                .build();
    }

    @Override
    public void deleteCategory(String categoryId) {

        categoryRepository.deleteById(categoryId);

    }

    @Override
    public CategoryResponse updateCategory(UpdateCategoryRequest updateCategoryRequest) {

        //To check weather the category exist.
        CategoryDAO getCategory =
                categoryRepository.findById(updateCategoryRequest.getCategoryId()).get();

        CategoryDAO category = CategoryDAO.builder()
                .categoryId(updateCategoryRequest.getCategoryId())
                .categoryName(updateCategoryRequest.getCategoryName())
                .description(updateCategoryRequest.getDescription())
                .build();

        category.setCreatedAt(getCategory.getCreatedAt());

        categoryRepository.save(category);
        return CategoryResponse
                .builder()
                .id(category.getCategoryId())
                .name(category.getCategoryName())
                .description(category.getDescription())
                .imgUrl(category.getImgUrl())
                .build();
    }

    @Override
    public List<CategoryResponse> getCategories(String sort, Integer page, Integer size) {


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
        Page<CategoryDAO> entities = categoryRepository.findAll(pageable);
        List<CategoryResponse> responses = new ArrayList<>();
        entities.forEach(entity->{
            responses.add(CategoryResponse
                    .builder()
                    .id(entity.getCategoryId())
                    .name(entity.getCategoryName())
                    .description(entity.getDescription())
                    .imgUrl(entity.getImgUrl())
                    .build());
        });
        return responses;
    }
}
