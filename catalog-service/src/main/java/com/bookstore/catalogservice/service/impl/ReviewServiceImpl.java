package com.bookstore.catalogservice.service.impl;

import com.bookstore.catalogservice.common.util.CommonUtilityMethods;
import com.bookstore.catalogservice.dao.ReviewDAO;
import com.bookstore.catalogservice.repository.ReviewRepository;
import com.bookstore.catalogservice.service.ProductService;
import com.bookstore.catalogservice.service.ReviewService;
import com.bookstore.catalogservice.vo.request.CreateOrUpdateReviewRequest;
import com.bookstore.catalogservice.vo.resonse.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductService productService;


    @Override
    public void createOrUpdateReview(CreateOrUpdateReviewRequest createOrUpdateReviewRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = CommonUtilityMethods.getUserIdFromToken(authentication);
        String userName = CommonUtilityMethods.getUserNameFromToken(authentication);
        ProductResponse product = productService.getProduct(createOrUpdateReviewRequest.getProductId());
        if (product == null) {
            throw new RuntimeException("Product doesn't exist!");
        }
        Optional<ReviewDAO> reviewDAOOptional = reviewRepository.findByUserIdAndProductId(userId, product.getProductId());
        ReviewDAO reviewDAO;
        if(reviewDAOOptional.isPresent()){
            reviewDAO = reviewDAOOptional.get();
            reviewDAO.setReviewContent(createOrUpdateReviewRequest.getReviewContent());
            reviewDAO.setRatingValue(createOrUpdateReviewRequest.getRatingValue());
        }else{
            reviewDAO = ReviewDAO
                    .builder()
                    .userId(userId)
                    .userName(userName)
                    .reviewContent(createOrUpdateReviewRequest.getReviewContent())
                    .productId(createOrUpdateReviewRequest.getProductId())
                    .ratingValue(createOrUpdateReviewRequest.getRatingValue())
                    .build();
        }
        reviewRepository.save(reviewDAO);
    }

    @Override
    public List<ReviewDAO> getReviewsForProduct(String productId) {
        Optional<List<ReviewDAO>> reviewsForProduct = reviewRepository.findAllByProductId(productId);
        return reviewsForProduct.orElseGet(ArrayList::new);
    }
}
