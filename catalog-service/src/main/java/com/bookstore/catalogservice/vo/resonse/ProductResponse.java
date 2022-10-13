package com.bookstore.catalogservice.vo.resonse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash
public class ProductResponse {

    private String productId;
    private String productName;
    private String description;
    private double price;
    private String categoryName;
    private String categoryId;
    private String producerId;
    private String producerName;
    private int availableItemCount;
    private Double averageRating;
    private int noOfRatings;
    private List<String> images;

}