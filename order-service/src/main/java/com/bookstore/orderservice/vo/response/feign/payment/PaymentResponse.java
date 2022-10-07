package com.bookstore.orderservice.vo.response.feign.payment;

import com.bookstore.orderservice.utils.feign.PaymentType;
import com.bookstore.orderservice.utils.feign.PaymentStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentResponse {
    private String urlPayment;
    private PaymentType paymentType;
    private PaymentStatus status;
}
