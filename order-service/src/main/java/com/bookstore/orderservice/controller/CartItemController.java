package com.bookstore.orderservice.controller;

import com.bookstore.orderservice.service.CartItemService;
import com.bookstore.orderservice.vo.request.CartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class CartItemController {

    @Autowired
    CartItemService cartItemService;
    
    @PostMapping("/cart/cartItem")
    @ResponseStatus(value = HttpStatus.OK)
    public void addCartItem(@RequestBody CartItemRequest cartItemRequest) {
        cartItemService.addCartItem(cartItemRequest);
    }
    
    @DeleteMapping("/cart/cartItem/{cartItemId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeCartItem(@PathVariable(value = "cartItemId") String cartItemId) {
        cartItemService.removeCartItem(cartItemId);
    }
    
    @DeleteMapping("/cart/cartItem")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeAllCartItems(@RequestParam(value = "cartId") String cartId) {
        cartItemService.removeAllCartItems(cartId);
    }
}