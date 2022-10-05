package com.bookstore.orderservice.vo.response;

import com.bookstore.orderservice.utils.feign.PaymentEnums;
import com.bookstore.orderservice.utils.feign.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePaymentResponse {
    private String urlPayment;
    private PaymentEnums paymentType;
    private PaymentStatus status;
}