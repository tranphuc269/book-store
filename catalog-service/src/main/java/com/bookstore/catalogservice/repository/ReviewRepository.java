package com.bookstore.catalogservice.repository;

import com.bookstore.catalogservice.dao.ReviewDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReviewRepository extends JpaRepository<ReviewDAO, String> {

    Optional<ReviewDAO> findByUserIdAndProductId(String userId, String productId);

    Optional<List<ReviewDAO>> findAllByProductId(String productId);

    //    long countAllByProductIdAndReviewMessageNotNull (String productId);
    long countAllByProductId(String productId);
}