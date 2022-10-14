package com.bookstore.searchservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private String productId;
    private int availableItemCount;
    private String productDescription;
    private List<String> images;
    private Double price;
    private String productName;
    private String categoryId;
    private String producerId;
}
