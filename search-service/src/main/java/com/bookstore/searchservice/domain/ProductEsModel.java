package com.bookstore.searchservice.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "product")
@Builder
public class ProductEsModel {
    private Long id;
    private String productId;
    private int availableItemCount;
    private String productDescription;
    private List<String> images;
    private Double price;
    private String productName;
    private String categoryId;
    private String producerId;
}
