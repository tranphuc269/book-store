package com.bookstore.searchservice.service.impl;

import com.bookstore.searchservice.domain.dao.ProductDAO;
import com.bookstore.searchservice.domain.dto.ProductDTO;
import com.bookstore.searchservice.repository.IProductDAORepository;
import com.bookstore.searchservice.service.IProductDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductDAOService implements IProductDAOService {


    @Autowired
    private IProductDAORepository productDAORepository;

    @Override
    public ProductDTO save(ProductDAO productDAO) {
        ProductDAO product = productDAORepository.save(productDAO);
        return ProductDTO
                .builder()
                .productId(product.getProductId())
                .availableItemCount(product.getAvailableItemCount())
                .productName(product.getProductName())
                .categoryId(product.getCategoryId())
                .images(product.getListImages())
                .productDescription(product.getDescription())
                .producerId(product.getProducerId())
                .price(product.getPrice())
                .build();
    }

    @Override
    public ProductDTO findById(String id) {
        ProductDAO product = productDAORepository.findById(id).orElseThrow(() -> new RuntimeException("Product doesn't exist!"));
        return ProductDTO
                .builder()
                .productId(product.getProductId())
                .categoryId(product.getCategoryId())
                .producerId(product.getProducerId())
                .availableItemCount(product.getAvailableItemCount())
                .price(product.getPrice())
                .productName(product.getProductName())
                .productDescription(product.getDescription())
                .images(product.getListImages())
                .build();
    }

    @Override
    public List<ProductDTO> findAll() {
        List<ProductDTO> productDTOS = new ArrayList<>();
        List<ProductDAO> productDAOS = productDAORepository.findAll();
        productDAOS.forEach(product -> {
            productDTOS.add(ProductDTO
                    .builder()
                    .productId(product.getProductId())
                    .categoryId(product.getCategoryId())
                    .producerId(product.getProducerId())
                    .availableItemCount(product.getAvailableItemCount())
                    .price(product.getPrice())
                    .productName(product.getProductName())
                    .productDescription(product.getDescription())
                    .images(product.getListImages())
                    .build());
        });
        return productDTOS;
    }
}