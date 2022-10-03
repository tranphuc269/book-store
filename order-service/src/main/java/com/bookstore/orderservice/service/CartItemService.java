package com.bookstore.orderservice.service;

import com.bookstore.orderservice.dao.CartItemDAO;
import com.bookstore.orderservice.vo.request.CartItemRequest;


public interface CartItemService {

    void addCartItem(CartItemRequest cartItemRequest);
    void removeCartItem(String cartItemId);
    CartItemDAO getCartItem(String cartItemId);
    void removeAllCartItems(String cartId);

}