package com.bookstore.orderservice.controller;

import com.bookstore.orderservice.common.response.CommonResult;
import com.bookstore.orderservice.dao.CartDAO;
import com.bookstore.orderservice.service.CartService;
import com.bookstore.orderservice.vo.response.CreateCartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/cart")
    @PreAuthorize("hasAuthority('STANDARD_USER') or hasAuthority('ADMIN_USER')" )
    public ResponseEntity<CreateCartResponse> createCart() {

        String cartId = cartService.createCart();

        CreateCartResponse createCartResponse = CreateCartResponse.builder()
                .cartId(cartId)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(createCartResponse);
    }
    
    @GetMapping("/cart")
    public CommonResult<CartDAO> getCart(){
        return CommonResult.success(cartService.getCart());
    }

}