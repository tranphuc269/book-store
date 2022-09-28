package com.bookstore.catalogservice.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCategoryRequest {

    @NotNull(message = "category name should not be null!")
    @NotEmpty(message = "category name should not be empty!")
    private String categoryName;
    private String description;
    private String imgUrl;

}