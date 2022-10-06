package com.bookstore.paymentservice.vo.request;


import com.bookstore.paymentservice.utils.PaymentEnums;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentRequest {
    private Long amount;
    private String information;
    private String orderId;
    private PaymentEnums paymentType;

}
