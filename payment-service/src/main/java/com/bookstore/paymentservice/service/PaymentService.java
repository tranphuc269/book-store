package com.bookstore.paymentservice.service;

import com.bookstore.paymentservice.vo.request.CreatePaymentMethod;
import com.bookstore.paymentservice.vo.request.PaymentRequest;
import com.bookstore.paymentservice.vo.response.PaymentMethodResponse;
import com.bookstore.paymentservice.vo.response.PaymentResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface PaymentService {
    public PaymentResponse paymentWithVNPay(PaymentRequest paymentRequest, HttpServletRequest request) throws UnsupportedEncodingException;

    public PaymentResponse paymentWithBankTransfer(PaymentRequest paymentRequest);

    public PaymentResponse paymentWithCOD(PaymentRequest paymentRequest);

    public List<PaymentMethodResponse> getPaymentMethods();

    public PaymentMethodResponse createPaymentMethod(CreatePaymentMethod createPaymentMethod);
}
