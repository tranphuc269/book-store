package com.bookstore.catalogservice.repository;

import com.bookstore.catalogservice.dao.ProductDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<ProductDAO, String> {
    @Query(
            value = "SELECT * FROM products p WHERE p.producer_id = ?1",
            nativeQuery = true)
    List<ProductDAO> getProductDAOSByProducerId(String producerId);

    @Query(
            value = "SELECT * FROM products p WHERE p.category_id = ?1",
            nativeQuery = true)
    List<ProductDAO> getProductDAOSByCategoryId(String categoryId);
}