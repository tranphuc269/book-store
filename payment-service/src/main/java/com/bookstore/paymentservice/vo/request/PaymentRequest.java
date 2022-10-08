package com.bookstore.paymentservice.vo.request;


import com.bookstore.paymentservice.utils.PaymentType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentRequest {
    private Long amount;
    private String information;
    private String orderId;
    private PaymentType paymentType;

}
