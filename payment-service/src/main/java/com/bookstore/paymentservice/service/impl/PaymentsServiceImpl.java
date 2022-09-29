package com.bookstore.paymentservice.service.impl;

import com.bookstore.paymentservice.common.exception.RunTimeExceptionPlaceHolder;
import com.bookstore.paymentservice.common.util.CommonUtilityMethods;
import com.bookstore.paymentservice.dao.UserPaymentCustomer;
import com.bookstore.paymentservice.repository.UserPaymentCustomerRepository;
import com.bookstore.paymentservice.service.PaymentsService;
import com.bookstore.paymentservice.vo.request.CreatePaymentRequest;
import com.bookstore.paymentservice.vo.response.CreatePaymentResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;


/**
 * @author Devaraj Reddy, Date : 25-Jul-2020
 */
@Service
public class PaymentsServiceImpl implements PaymentsService {

    @Autowired
    private UserPaymentCustomerRepository userPaymentCustomerRepository;

    @Override
    public CreatePaymentResponse createPaymentRequest(CreatePaymentRequest createPaymentRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userIdFromToken = CommonUtilityMethods.getUserIdFromToken(authentication);
        UserPaymentCustomer customer = userPaymentCustomerRepository.findByUserId(userIdFromToken);

        Map<String, Object> params = new HashMap<>();
        params.put("amount", createPaymentRequest.getAmount());
        params.put("currency", createPaymentRequest.getCurrency());
        params.put("payment_method", createPaymentRequest.getPaymentMethodId());
        params.put("customer", customer.getPaymentId());
        params.put("confirm", true);

        try {
            PaymentIntent paymentIntent = PaymentIntent.create(params);
            CreatePaymentResponse createPaymentResponse = new CreatePaymentResponse();

            Optional<Charge> paidRecord = paymentIntent.getCharges().getData().stream().filter(Charge::getPaid).findAny();

            if (paidRecord.isPresent()) {
                createPaymentResponse.setPaymentId(paidRecord.get().getId());
                LocalDateTime paymentTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(paidRecord.get().getCreated()), TimeZone.getDefault().toZoneId());
                createPaymentResponse.setPaymentDate(paymentTime);
                createPaymentResponse.setCaptured(true);
                createPaymentResponse.setReceiptUrl(paidRecord.get().getReceiptUrl());
                return createPaymentResponse;
            } else {
                createPaymentResponse.setCaptured(false);
                return createPaymentResponse;
            }

        } catch (StripeException e) {
            e.printStackTrace();
            throw new RunTimeExceptionPlaceHolder("Error while doing payment!!");
        }

    }
}