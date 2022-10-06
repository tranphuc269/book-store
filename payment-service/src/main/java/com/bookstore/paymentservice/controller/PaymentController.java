package com.bookstore.paymentservice.controller;

import com.bookstore.paymentservice.common.response.BookStoreResponse;
import com.bookstore.paymentservice.service.PaymentService;
import com.bookstore.paymentservice.utils.BookStoreConstant;
import com.bookstore.paymentservice.vo.request.CreatePaymentMethod;
import com.bookstore.paymentservice.vo.request.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<?> pay(@RequestBody PaymentRequest paymentRequest, HttpServletRequest request) {
        try {
            switch (paymentRequest.getPaymentType()) {

                case VNPAY:
                    return ResponseEntity.ok(paymentService.paymentWithVNPay(paymentRequest, request));
                case COD:
                    return ResponseEntity.ok(paymentService.paymentWithCOD(paymentRequest));
                case BANKING:
                    return ResponseEntity.ok(paymentService.paymentWithBankTransfer(paymentRequest));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BookStoreResponse
                    .builder()
                    .data(null)
                    .success(false)
                    .message(BookStoreConstant.ERROR_MESSAGE)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BookStoreResponse
                    .builder()
                    .data(null)
                    .success(false)
                    .message(BookStoreConstant.ERROR_MESSAGE)
                    .build());
        }
    }

    @GetMapping("/paymentMethod")
    public ResponseEntity<?> getPaymentMethods() {
        return ResponseEntity.ok(BookStoreResponse
                .builder()
                .data(paymentService.getPaymentMethods())
                .success(true)
                .message("")
                .build());
    }

    @PostMapping("/paymentMethod")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<?> createPaymentMethod(@RequestBody CreatePaymentMethod createPaymentMethod) {
        return ResponseEntity.ok(BookStoreResponse
                .builder()
                .data(paymentService.createPaymentMethod(createPaymentMethod))
                .success(true)
                .message("")
                .build());
    }
}
