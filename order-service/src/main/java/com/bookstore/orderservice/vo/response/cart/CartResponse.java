package com.bookstore.orderservice.vo.response.cart;


import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {
    String cartId;
    String userId;
    double totalPrice;
    List<CartItemResponse> data;
}
