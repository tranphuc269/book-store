package com.bookstore.orderservice.vo.response.feign.payment;

import com.bookstore.orderservice.utils.feign.PaymentEnums;
import com.bookstore.orderservice.utils.feign.PaymentStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentResponse {
    private String urlPayment;
    private PaymentEnums paymentType;
    private PaymentStatus status;
}
