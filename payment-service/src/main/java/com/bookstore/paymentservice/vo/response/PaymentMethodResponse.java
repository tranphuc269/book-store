package com.bookstore.paymentservice.vo.response;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentMethodResponse {
    private String paymentMethodId;
    private String paymentMethodName;
    private String paymentMethodDescription;
    private String paymentMethodType;
}
