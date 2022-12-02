package com.bookstore.orderservice.vo.response.feign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeignProductResponse {
    int code;
    String message;
    FeignProductData data;
}