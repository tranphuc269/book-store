package com.bookstore.catalogservice.service.impl;

import com.bookstore.catalogservice.dao.CategoryDAO;
import com.bookstore.catalogservice.dao.ProducerDAO;
import com.bookstore.catalogservice.dao.ProductDAO;
import com.bookstore.catalogservice.dao.ReviewDAO;
import com.bookstore.catalogservice.repository.CategoryRepository;
import com.bookstore.catalogservice.repository.ProducerRepository;
import com.bookstore.catalogservice.repository.ProductRepository;
import com.bookstore.catalogservice.repository.ReviewRepository;
import com.bookstore.catalogservice.service.ProductService;
import com.bookstore.catalogservice.service.ReviewService;
import com.bookstore.catalogservice.vo.request.CreateProductRequest;
import com.bookstore.catalogservice.vo.resonse.ProductResponse;
import com.bookstore.catalogservice.vo.resonse.UpdateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProducerRepository producerRepository;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReviewRepository reviewRepository;


    @Override
    public String createProduct(CreateProductRequest createProductRequest) {
        Optional<CategoryDAO> categoryDAOOptional =
                categoryRepository.findById(createProductRequest.getCategoryId());

        CategoryDAO categoryDAO = categoryDAOOptional.orElseThrow(() -> new RuntimeException("Category doesn't exist!"));
        ProducerDAO producerDAO = (producerRepository.findById(createProductRequest.getProducerId())).orElseThrow(() -> new RuntimeException("Producer doesn't exits"));
        ProductDAO product = ProductDAO.builder()
                .productName(createProductRequest.getProductName())
                .description(createProductRequest.getDescription())
                .availableItemCount(createProductRequest.getAvailableItemCount())
                .price(createProductRequest.getPrice())
                .category(categoryDAO)
                .images(createProductRequest.getImage().toString())
                .producer(producerDAO)
                .author(createProductRequest.getAuthor())
                .weight(createProductRequest.getWeight())
                .dimension(createProductRequest.getDimension())
                .pageCount(createProductRequest.getPageCount())
                .build();


        ProductDAO savedProduct = productRepository.save(product);
        return savedProduct.getProductId();
    }

    @Override
    public ProductResponse getProduct(String productId) {
        Optional<ProductDAO> productDAOOptional = productRepository.findById(productId);
        if (productDAOOptional.isPresent()) {
            ProductDAO productDAO = productDAOOptional.get();
            List<ReviewDAO> reviews = reviewService.getReviewsForProduct(productId);
            return ProductResponse
                    .builder()
                    .categoryName(productDAO.getCategoryName())
                    .categoryId(productDAO.getCategoryId())
                    .productName(productDAO.getProductName())
                    .productId(productDAO.getProductId())
                    .description(productDAO.getDescription())
                    .price(productDAO.getPrice())
                    .availableItemCount(productDAO.getAvailableItemCount())
                    .noOfRatings(reviews.size())
                    .producerId(productDAO.getProducer().getProducerId())
                    .producerName(productDAO.getProducer().getProducerName())
                    .author(productDAO.getAuthor())
                    .weight(productDAO.getWeight())
                    .dimension(productDAO.getDimension())
                    .pageCount(productDAO.getPageCount())
                    .averageRating(reviews.stream()
                            .mapToDouble(ReviewDAO::getRatingValue)
                            .average()
                            .orElse(0.0))
                    .images(productDAO.getListImages())
                    .build();
        }
        return null;
    }

    @Override
    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public void updateProduct(UpdateProductRequest updateProductRequest) {
        Optional<ProductDAO> productOptional =
                productRepository.findById(updateProductRequest.getProductId());

        //check weather product exists
        final ProductDAO productExisting = productOptional.orElseThrow(() -> new RuntimeException("Product Id doesn't exist!"));

        productExisting.setProductId(updateProductRequest.getProductId());

        if (updateProductRequest.getProductName() != null) {
            productExisting.setProductName(updateProductRequest.getProductName());
        }

        if (updateProductRequest.getDescription() != null) {
            productExisting.setDescription(updateProductRequest.getDescription());
        }

        if (updateProductRequest.getPrice() != null) {
            productExisting.setPrice(updateProductRequest.getPrice());
        }

        if (updateProductRequest.getImage() != null) {
            productExisting.setImages(updateProductRequest.getImage());
        }

        if (updateProductRequest.getCategoryId() != null) {
            Optional<CategoryDAO> categoryDAOOptional =
                    categoryRepository.findById(updateProductRequest.getCategoryId());

            //check weather product category exists
            CategoryDAO category = categoryDAOOptional.orElseThrow(() -> new RuntimeException("Category doesn't exist!"));
            productExisting.setCategory(category);
        }

        if (updateProductRequest.getAvailableItemCount() != null) {
            productExisting.setAvailableItemCount(updateProductRequest.getAvailableItemCount());
        }

        productExisting.setCreatedAt(productExisting.getCreatedAt());

        productRepository.save(productExisting);
    }

    @Override
    public List<ProductResponse> getProductByProducerId(String producerId) {
        List<ProductDAO> productDaos = productRepository.getProductDAOSByProducerId(producerId);
        return getProductResponses(productDaos);
    }

    @Override
    public List<ProductResponse> getProductByCategoryId(String categoryId) {
        List<ProductDAO> productDaos = productRepository.getProductDAOSByCategoryId(categoryId);
        return getProductResponses(productDaos);
    }

    private List<ProductResponse> getProductResponses(List<ProductDAO> productDaos) {
        List<ProductResponse> products = new ArrayList<>();
        productDaos.forEach(v -> {
            List<ReviewDAO> reviews = reviewService.getReviewsForProduct(v.getProductId());
            products.add(ProductResponse
                    .builder()
                    .categoryName(v.getCategoryName())
                    .categoryId(v.getCategoryId())
                    .producerName(v.getProducer().getProducerName())
                    .producerId(v.getProducer().getProducerId())
                    .productName(v.getProductName())
                    .productId(v.getProductId())
                    .author(v.getAuthor())
                    .weight(v.getWeight())
                    .dimension(v.getDimension())
                    .pageCount(v.getPageCount())
                    .description(v.getDescription())
                    .price(v.getPrice())
                    .availableItemCount(v.getAvailableItemCount())
                    .noOfRatings(reviews.size())
                    .averageRating(reviews.stream()
                            .mapToDouble(ReviewDAO::getRatingValue)
                            .average()
                            .orElse(0.0))
                    .images(v.getListImages())
                    .build());
        });
        return products;
    }

    @Override
    public Page<ProductDAO> findAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<ProductResponse> getAllProducts(String sort, Integer page, Integer size) {
        if (size == null || size == 0) {
            size = 20;
        }

        //set defaults
        if (page == null || page == 0) {
            page = 0;
        }

        Pageable pageable;

        if (sort == null) {
            pageable = PageRequest.of(page, size);
        } else {
            Sort.Order order;

            try {
                String[] split = sort.split(",");

                Sort.Direction sortDirection = Sort.Direction.fromString(split[1]);
                order = new Sort.Order(sortDirection, split[0]).ignoreCase();
                pageable = PageRequest.of(page, size, Sort.by(order));

            } catch (Exception e) {
                throw new RuntimeException("Not a valid sort value, It should be 'fieldName,direction', example : 'productName,asc");
            }

        }
        Page<ProductDAO> allProducts = productRepository.findAll(pageable);
        Page<ProductResponse> allProductsResponse = allProducts.map(ProductDAO::fromEntity);
        allProductsResponse.forEach(productResponse -> populateRatingForProduct(productResponse.getProductId(), productResponse));

        return allProductsResponse;
    }

    //This way of setting rating for productResponse is not okay, But this is okay for now.
    private void populateRatingForProduct(String productId, ProductResponse productResponse) {
        List<ReviewDAO> reviewsForProduct = reviewService.getReviewsForProduct(productId);
        if (reviewsForProduct.size() > 0) {
            double sum = reviewsForProduct.stream().mapToDouble(ReviewDAO::getRatingValue).sum();
            double rating = sum / reviewsForProduct.size();
            productResponse.setAverageRating(rating);
        }

        productResponse.setNoOfRatings(Math.toIntExact(reviewRepository.countAllByProductId(productId)));
    }
}
