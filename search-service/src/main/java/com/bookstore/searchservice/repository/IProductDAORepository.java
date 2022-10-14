package com.bookstore.searchservice.repository;

import com.bookstore.searchservice.domain.dao.ProductDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductDAORepository extends JpaRepository<ProductDAO, String> {
}
