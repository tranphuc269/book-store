package com.bookstore.orderservice.service.impl;

import com.bookstore.orderservice.dao.CartItemDAO;
import com.bookstore.orderservice.service.CartItemService;
import com.bookstore.orderservice.vo.request.CartItemRequest;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Override
    public void addCartItem(CartItemRequest cartItemRequest) {

    }

    @Override
    public void removeCartItem(String cartItemId) {

    }

    @Override
    public CartItemDAO getCartItem(String cartItemId) {
        return null;
    }

    @Override
    public void removeAllCartItems(String cartId) {

    }
}
