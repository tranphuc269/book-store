package com.bookstore.catalogservice.service;

import com.bookstore.catalogservice.dao.ReviewDAO;
import com.bookstore.catalogservice.vo.request.CreateOrUpdateReviewRequest;

import java.util.List;


public interface ReviewService {

    void createOrUpdateReview(CreateOrUpdateReviewRequest createOrUpdateReviewRequest);

    List<ReviewDAO> getReviewsForProduct(String productId);

}