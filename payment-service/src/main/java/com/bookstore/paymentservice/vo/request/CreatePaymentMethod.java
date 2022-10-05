package com.bookstore.paymentservice.vo.request;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatePaymentMethod {
    private String paymentMethodName;
    private String paymentMethodDescription;
    private String paymentType;
}
