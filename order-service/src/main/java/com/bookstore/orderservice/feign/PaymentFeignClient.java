package com.bookstore.orderservice.feign;

import com.bookstore.orderservice.vo.request.CreatePaymentRequest;
import com.bookstore.orderservice.vo.request.GetPaymentMethodResponse;
import com.bookstore.orderservice.vo.response.CreatePaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient("payment-service")
public interface PaymentFeignClient {

    @GetMapping("/paymentMethod/{paymentMethodId}")
    GetPaymentMethodResponse getMyPaymentMethodById(@PathVariable("paymentMethodId") String paymentMethodId);

    @PostMapping("/pay")
    CreatePaymentResponse doPayment(CreatePaymentRequest createPaymentRequest);
}