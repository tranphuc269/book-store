package com.bookstore.paymentservice.vo.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentResponse {
    private String urlPayment;
}
