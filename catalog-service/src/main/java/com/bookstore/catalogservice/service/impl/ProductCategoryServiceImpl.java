package com.bookstore.catalogservice.service.impl;

import com.bookstore.catalogservice.dao.ProductCategoryDAO;
import com.bookstore.catalogservice.repository.ProductCategoryRepository;
import com.bookstore.catalogservice.service.ProductCategoryService;
import com.bookstore.catalogservice.vo.request.CreateProductCategoryRequest;
import com.bookstore.catalogservice.vo.request.UpdateProductCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public String createProductCategory(
            @Valid CreateProductCategoryRequest createProductCategoryRequest) {

        ProductCategoryDAO productCategory = ProductCategoryDAO.builder()
                .productCategoryName(createProductCategoryRequest.getProductCategoryName())
                .description(createProductCategoryRequest.getDescription())
                .build();

        ProductCategoryDAO savedProductCategory = productCategoryRepository.save(productCategory);
        return savedProductCategory.getProductCategoryId();
    }

    @Override
    public ProductCategoryDAO getProductCategory(String productCategoryId) {

        Optional<ProductCategoryDAO> productCategoryOptional = productCategoryRepository.findById(productCategoryId);

        ProductCategoryDAO productCategory = productCategoryOptional.orElseThrow(() -> new RuntimeException("Product Category doesn't exist!"));

        return productCategory;
    }

    @Override
    public void deleteProductCategory(String productCategoryId) {

        productCategoryRepository.deleteById(productCategoryId);

    }

    @Override
    public void updateProductCategory(UpdateProductCategoryRequest updateProductCategoryRequest) {

        //To check weather the ProductCategory exist.
        ProductCategoryDAO getProductCategory =
                this.getProductCategory(updateProductCategoryRequest.getProductCategoryId());

        ProductCategoryDAO productCategory = ProductCategoryDAO.builder()
                .productCategoryId(updateProductCategoryRequest.getProductCategoryId())
                .productCategoryName(updateProductCategoryRequest.getProductCategoryName())
                .description(updateProductCategoryRequest.getDescription())
                .build();

        productCategory.setCreatedAt(getProductCategory.getCreatedAt());

        productCategoryRepository.save(productCategory);

    }

    @Override
    public Page<ProductCategoryDAO> getAllProductCategories(String sort, Integer page, Integer size) {

        //set defaults
        if (size == null || size == 0) {
            size = 20;
        }

        //set defaults
        if (page == null || page == 0) {
            page = 0;
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
                throw new RuntimeException("Not a valid sort value, It should be 'fieldName,direction', example : 'productCategoryName,asc");
            }

        }

        return productCategoryRepository.findAll(pageable);
    }
}
