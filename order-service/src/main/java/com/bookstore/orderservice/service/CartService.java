package com.bookstore.orderservice.service;

import com.bookstore.orderservice.dao.CartDAO;
import com.bookstore.orderservice.vo.response.cart.CartResponse;

public interface CartService {

    CartDAO getCart();

    CartDAO getCartByCartId(String cartId);

    String createCart();

    CartDAO getCartByUserId(String userId);

}