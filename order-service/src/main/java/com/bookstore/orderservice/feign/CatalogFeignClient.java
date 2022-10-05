package com.bookstore.orderservice.feign;

import com.bookstore.orderservice.vo.response.feign.GetProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient("catalog-service")
public interface CatalogFeignClient {
    @GetMapping("/product/{productId}")
    GetProductResponse getProduct(@PathVariable("productId") String productId);

}