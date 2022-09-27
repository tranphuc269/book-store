package com.bookstore.catalogservice.repository;

import com.bookstore.catalogservice.dao.ProductDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<ProductDAO, String> {
}