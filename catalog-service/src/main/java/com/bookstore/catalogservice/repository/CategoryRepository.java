package com.bookstore.catalogservice.repository;

import com.bookstore.catalogservice.dao.CategoryDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@EnableRedisRepositories
public interface CategoryRepository extends JpaRepository<CategoryDAO, String> {
}