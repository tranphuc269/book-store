package com.bookstore.searchservice.utils;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SourceProduct {
    String productId;
    int availableItemCount;
    String productDescription;
    List<String> images;
    double price;
    String productName;
    String categoryId;
    String producerId;
}
