package com.bookstore.orderservice.controller;

import com.bookstore.orderservice.common.response.CommonResult;
import com.bookstore.orderservice.dao.CartDAO;
import com.bookstore.orderservice.service.CartService;
import com.bookstore.orderservice.vo.response.cart.CartItemResponse;
import com.bookstore.orderservice.vo.response.cart.CartResponse;
import com.bookstore.orderservice.vo.response.order.CreateCartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/cart")
    @PreAuthorize("hasAuthority('STANDARD_USER') or hasAuthority('ADMIN_USER')")
    public ResponseEntity<CreateCartResponse> createCart() {

        String cartId = cartService.createCart();

        CreateCartResponse createCartResponse = CreateCartResponse.builder()
                .cartId(cartId)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(createCartResponse);
    }

    @GetMapping("/cart")
    public CommonResult<CartResponse> getCart() {
        CartDAO cartDAO = cartService.getCart();
        List<CartItemResponse> cartItemResponseList = new ArrayList<>();
        cartDAO.getCartItemDAOS().forEach(v -> {
            cartItemResponseList.add(CartItemResponse
                    .builder()
                    .cartItemId(v.getCartItemId())
                    .extendedPrice(v.getExtendedPrice())
                    .quantity(v.getQuantity())
                    .itemPrice(v.getItemPrice())
                    .images(v.getListImages())
                    .productName(v.getProductName())
                    .productId(v.getProductId())
                    .build());
        });
        return CommonResult.success(CartResponse
                .builder()
                .cartId(cartDAO.getCartId())
                .userId(cartDAO.getUserId())
                .totalPrice(cartDAO.getTotalPrice())
                .data(cartItemResponseList)
                .build());
    }
}