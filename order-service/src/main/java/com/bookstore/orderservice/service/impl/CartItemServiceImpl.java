package com.bookstore.orderservice.service.impl;

import com.bookstore.orderservice.common.util.CommonUtilityMethods;
import com.bookstore.orderservice.dao.CartDAO;
import com.bookstore.orderservice.dao.CartItemDAO;
import com.bookstore.orderservice.feign.AccountFeignClient;
import com.bookstore.orderservice.feign.CatalogFeignClient;
import com.bookstore.orderservice.repository.CartItemRepository;
import com.bookstore.orderservice.service.CartItemService;
import com.bookstore.orderservice.service.CartService;
import com.bookstore.orderservice.vo.request.CartItemRequest;
import com.bookstore.orderservice.vo.response.feign.FeignProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl implements CartItemService {

    private
    CartService cartService;

    private
    CatalogFeignClient catalogFeignClient;

    private
    CartItemRepository cartItemRepository;

    private
    AccountFeignClient accountFeignClient;


    @Autowired
    public CartItemServiceImpl(CartService cartService,
                               CatalogFeignClient catalogFeignClient,
                               CartItemRepository cartItemRepository,
                               AccountFeignClient accountFeignClient) {
        this.cartService = cartService;
        this.catalogFeignClient = catalogFeignClient;
        this.cartItemRepository = cartItemRepository;
        this.accountFeignClient = accountFeignClient;
    }

    @Override
    public void addCartItem(CartItemRequest cartItemRequest) {
        String userId;
        if(cartItemRequest.getUserId() != null){
            userId = cartItemRequest.getUserId();
        }else{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            userId = CommonUtilityMethods.getUserIdFromToken(authentication);
        }

        CartDAO cartByUserId = cartService.getCartByUserId(userId);

        synchronized (CartServiceImpl.class) {
            if (cartByUserId == null) {
                cartService.createCart();
                cartByUserId = cartService.getCartByUserId(userId);
            }
        }

        FeignProductResponse feignProductResponse = catalogFeignClient.getProduct(cartItemRequest.getProductId());
        //If the product already exists in the cart, update its quantity and itemPrice.
        if (cartByUserId.getCartItemDAOS() != null) {
            for (CartItemDAO ci : cartByUserId.getCartItemDAOS()) {
                if (feignProductResponse.getData().getProductId().equals(ci.getProductId())) {
                    ci.setQuantity(cartItemRequest.getQuantity());
                    ci.setItemPrice(feignProductResponse.getData().getPrice());
                    ci.setExtendedPrice(ci.getQuantity() * feignProductResponse.getData().getPrice());
                    cartItemRepository.save(ci);
                    return;
                }
            }
        }
        //If cart doesn't have any cartItems, then create cartItems.
        CartItemDAO cartItem = CartItemDAO.builder()
                .cart(cartByUserId)
                .itemPrice(feignProductResponse.getData().getPrice())
                .extendedPrice(cartItemRequest.getQuantity() * feignProductResponse.getData().getPrice())
                .quantity(cartItemRequest.getQuantity())
                .productId(feignProductResponse.getData().getProductId())
                .productName(feignProductResponse.getData().getProductName())
                .images(feignProductResponse.getData().getImages().toString())
                .build();
        cartItemRepository.save(cartItem);


    }

    @Override
    public void removeCartItem(String cartItemId) {
        CartItemDAO cartItem = this.getCartItem(cartItemId);
        cartItemRepository.delete(cartItem);
    }

    @Override
    public CartItemDAO getCartItem(String cartItemId) {
        Optional<CartItemDAO> byCartItemId = cartItemRepository.findByCartItemId(cartItemId);
        return byCartItemId.orElseThrow(() -> new RuntimeException("CartItem doesn't exist!!"));
    }

    @Override
    public void removeAllCartItems(String cartId) {
        CartDAO cart = cartService.getCartByCartId(cartId);
        List<String> cartItemIds = cart.getCartItemDAOS().stream().map(CartItemDAO::getCartItemId).collect(Collectors.toList());
        if (!cartItemIds.isEmpty()) {
            cartItemIds.forEach(this::removeCartItem);
        }
    }
}
