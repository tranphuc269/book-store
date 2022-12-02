package com.bookstore.orderservice.vo.response.feign;

import com.bookstore.orderservice.utils.feign.PaymentType;
import com.bookstore.orderservice.utils.feign.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeignPaymentResponse {
    private String urlPayment;
    private PaymentType paymentType;
    private PaymentStatus status;
}