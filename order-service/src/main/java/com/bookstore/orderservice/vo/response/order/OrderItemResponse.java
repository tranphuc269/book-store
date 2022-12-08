package com.bookstore.orderservice.vo.response.order;


import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemResponse {
    String orderItemId;
    String productId;

    int quantity;
    double orderItemPrice;
    double orderExtendedPrice;
    List<String> images;
}
