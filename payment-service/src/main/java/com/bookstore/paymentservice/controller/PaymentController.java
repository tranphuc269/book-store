package com.bookstore.paymentservice.controller;

import com.bookstore.paymentservice.service.PaymentService;
import com.bookstore.paymentservice.vo.request.CreatePaymentMethod;
import com.bookstore.paymentservice.vo.request.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<?> pay(@RequestBody PaymentRequest paymentRequest, HttpServletRequest request){
        try {
            return ResponseEntity.ok(paymentService.paymentWithVNPay(paymentRequest, request));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/paymentMethod")
    public ResponseEntity<?> getPaymentMethods(){
        return ResponseEntity.ok(paymentService.getPaymentMethods());
    }

    @PostMapping("/paymentMethod")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<?> createPaymentMethod(@RequestBody CreatePaymentMethod createPaymentMethod){
        return ResponseEntity.ok(paymentService.createPaymentMethod(createPaymentMethod));
    }
}
