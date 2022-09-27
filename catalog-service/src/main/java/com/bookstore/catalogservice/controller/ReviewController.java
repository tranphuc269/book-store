package com.bookstore.catalogservice.controller;

import com.bookstore.catalogservice.dao.ReviewDAO;
import com.bookstore.catalogservice.service.ReviewService;
import com.bookstore.catalogservice.vo.request.CreateOrUpdateReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/review")
    public ResponseEntity<?> createOrUpdateReview(@RequestBody @Valid CreateOrUpdateReviewRequest createOrUpdateReviewRequest) {

        reviewService.createOrUpdateReview(createOrUpdateReviewRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/review")
    public ResponseEntity<?> getAllReviewsForProduct(@RequestParam("productId") String productId) {
        List<ReviewDAO> reviewsForProduct = reviewService.getReviewsForProduct(productId);
        return ResponseEntity.ok(reviewsForProduct);
    }
}