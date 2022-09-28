package com.bookstore.catalogservice.repository;

import com.bookstore.catalogservice.dao.CategoryDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<CategoryDAO, String> {
}