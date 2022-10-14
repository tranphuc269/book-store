package com.bookstore.searchservice.service;

import com.bookstore.searchservice.domain.dao.ProductDAO;
import com.bookstore.searchservice.domain.dto.ProductDTO;

import java.util.List;

public interface IProductDAOService {
    ProductDTO save(ProductDAO productDAO);
    ProductDTO findById(String id);
    List<ProductDTO> findAll();
}
