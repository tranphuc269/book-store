package com.bookstore.paymentservice.service;

import com.bookstore.paymentservice.vo.request.CreatePaymentMethodRequest;
import com.bookstore.paymentservice.vo.response.GetPaymentMethodResponse;

import java.util.List;


public interface PaymentMethodService {
    void createPaymentMethod(CreatePaymentMethodRequest createPaymentMethodRequest);

    List<GetPaymentMethodResponse> getAllMyPaymentMethods();

    GetPaymentMethodResponse getMyPaymentMethodById(String paymentMethodId);
}