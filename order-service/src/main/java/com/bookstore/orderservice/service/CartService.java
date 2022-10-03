package com.bookstore.orderservice.service;

import com.bookstore.orderservice.dao.CartDAO;

public interface CartService {

    CartDAO getCart();

    CartDAO getCartByCartId(String cartId);

    String createCart();

    CartDAO getCartByUserId(String userId);

}