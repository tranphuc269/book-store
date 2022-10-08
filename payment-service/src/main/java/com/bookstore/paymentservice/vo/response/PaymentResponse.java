package com.bookstore.paymentservice.vo.response;

import com.bookstore.paymentservice.utils.PaymentType;
import com.bookstore.paymentservice.utils.PaymentStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentResponse {
    private String urlPayment;
    private PaymentType paymentType;
    private PaymentStatus status;
}
