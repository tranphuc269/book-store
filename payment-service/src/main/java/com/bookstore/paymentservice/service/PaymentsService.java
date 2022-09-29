package com.bookstore.paymentservice.service;

import com.bookstore.paymentservice.vo.request.CreatePaymentRequest;
import com.bookstore.paymentservice.vo.response.CreatePaymentResponse;

public interface PaymentsService {
    CreatePaymentResponse createPaymentRequest(CreatePaymentRequest createPaymentRequest);
}