package com.bookstore.paymentservice.vo.response;

import com.bookstore.paymentservice.utils.PaymentEnums;
import com.bookstore.paymentservice.utils.PaymentStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentResponse {
    private String urlPayment;
    private PaymentEnums paymentType;
    private PaymentStatus status;
}
