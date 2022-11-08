package com.bookstore.searchservice.repository;

import com.bookstore.searchservice.domain.dao.ProductDAO;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.JpaRepository;

@EnableElasticsearchRepositories
public interface IProductDAORepository extends JpaRepository<ProductDAO, String> {
}
