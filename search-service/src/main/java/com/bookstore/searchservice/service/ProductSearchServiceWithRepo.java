package com.bookstore.searchservice.service;

import com.bookstore.searchservice.dao.Product;
import com.bookstore.searchservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSearchServiceWithRepo {

  @Autowired
  private ProductRepository productRepository;

  public void createProductIndexBulk(final List<Product> products) {
    productRepository.saveAll(products);
  }

  public void createProductIndex(final Product product) {
    productRepository.save(product);
  }
}