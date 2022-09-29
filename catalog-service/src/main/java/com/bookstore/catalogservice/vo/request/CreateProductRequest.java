package com.bookstore.catalogservice.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductRequest {

    @NotNull(message = "productName should not be null!")
    @NotEmpty(message = "productName should not be empty!")
    private String productName;

    private String description;

    @Min(value = 0)
    private double price;

    private List<String> image;

    @NotNull(message = "category id should not be null!")
    @NotEmpty(message = "category id should not be empty!")
    private String categoryId;

    @NotNull(message = "category id should not be null!")
    @NotEmpty(message = "category id should not be empty!")
    private String producerId;

    private int availableItemCount;

}