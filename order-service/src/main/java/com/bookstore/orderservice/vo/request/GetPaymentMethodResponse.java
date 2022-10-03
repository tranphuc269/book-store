package com.bookstore.orderservice.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetPaymentMethodResponse {

    private String paymentMethodId;
    private String cardType;
    private String cardLast4Digits;
    private Long cardExpirationMonth;
    private Long cardExpirationYear;
    private String cardCountry;

}