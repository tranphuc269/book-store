package com.bookstore.catalogservice.vo.resonse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private String productId;
    private String productName;
    private String description;
    private double price;
    private String categoryName;
    private int availableItemCount;
    private Double averageRating;
    private int noOfRatings;
    private List<String> images;

}