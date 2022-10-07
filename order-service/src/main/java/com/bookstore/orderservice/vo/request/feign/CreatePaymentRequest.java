package com.bookstore.orderservice.vo.request.feign;

import com.bookstore.orderservice.utils.feign.PaymentType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreatePaymentRequest {
    private Long amount;
    private String information;
    private String orderId;
    private PaymentType paymentType;

}