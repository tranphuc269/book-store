package com.bookstore.paymentservice.vo.request;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentRequest {
    private Long vnpAmount;
    private String vnpOrderInfo;
    private Long vnpTxnRef;

}
