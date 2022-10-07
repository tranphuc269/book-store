package com.bookstore.catalogservice.vo.request;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateCartItem {
    private String productId;
    private String quantity;
    private String userId;
}
