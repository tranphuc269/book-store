package com.bookstore.orderservice.vo.response.cart;


import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponse {
    String cartItemId;
    int quantity;
    double itemPrice;
    double extendedPrice;
    String productId;
    String productName;
    List<String> images;
}
