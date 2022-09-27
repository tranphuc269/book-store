package com.bookstore.catalogservice.repository;

import com.bookstore.catalogservice.dao.ProductCategoryDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategoryDAO, String> {
}