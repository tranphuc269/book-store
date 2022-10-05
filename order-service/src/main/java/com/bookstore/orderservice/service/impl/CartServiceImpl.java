package com.bookstore.orderservice.service.impl;

import com.bookstore.orderservice.common.exception.RunTimeExceptionPlaceHolder;
import com.bookstore.orderservice.common.util.CommonUtilityMethods;
import com.bookstore.orderservice.dao.CartDAO;
import com.bookstore.orderservice.dao.CartItemDAO;
import com.bookstore.orderservice.repository.CartRepository;
import com.bookstore.orderservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public CartDAO getCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userIdFromToken = CommonUtilityMethods.getUserIdFromToken(authentication);
        Optional<CartDAO> cartDAO = cartRepository.findCartByUserId(userIdFromToken);
        synchronized (CartServiceImpl.class) {
            if (cartDAO.isEmpty()) {
                createCart();
                cartDAO = cartRepository.findCartByUserId(userIdFromToken);
            }
        }
        double totalPrice = cartDAO.get().getCartItemDAOS()
                .stream()
                .mapToDouble(CartItemDAO::getExtendedPrice)
                .sum();

        cartDAO.get().setTotalPrice(totalPrice);
        return cartDAO.get();
    }

    @Override
    public CartDAO getCartByCartId(String cartId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<CartDAO> cartDAO = cartRepository.findByCartId(cartId);
        if (cartDAO.isPresent()) {
            return cartDAO.get();
        } else {
            throw new RunTimeExceptionPlaceHolder("Empty cart");
        }
    }

    @Override
    public String createCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = CommonUtilityMethods.getUserIdFromToken(authentication);
        Optional<CartDAO> currentCartDAO = cartRepository.findCartByUserId(userId);
        if(currentCartDAO.isPresent()){
            return  currentCartDAO.get().getCartId();
        }
        CartDAO cartDAO = CartDAO
                .builder()
                .cartItemDAOS(new ArrayList<>())
                .userId(userId)
                .build();
        CartDAO cartSave = cartRepository.save(cartDAO);
        return cartSave.getCartId();
    }

    @Override
    public CartDAO getCartByUserId(String userId) {
        Optional<CartDAO> cartDAO = cartRepository.findCartByUserId(userId);
        if (cartDAO.isPresent()) {
            return cartDAO.get();
        } else {
            throw new RunTimeExceptionPlaceHolder("Empty cart");
        }
    }
}