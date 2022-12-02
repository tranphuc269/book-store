package com.bookstore.orderservice.feign;

import com.bookstore.orderservice.vo.request.feign.CreatePaymentRequest;
import com.bookstore.orderservice.vo.response.feign.FeignPaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient("payment-service")
public interface PaymentFeignClient {
    @PostMapping("/payment")
    FeignPaymentResponse doPayment(CreatePaymentRequest createPaymentRequest);
}
