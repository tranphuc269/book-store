package com.bookstore.orderservice.service;

import com.bookstore.orderservice.vo.request.CreateOrderRequest;
import com.bookstore.orderservice.vo.request.PreviewOrderRequest;
import com.bookstore.orderservice.vo.response.order.CreateOrderResponse;
import com.bookstore.orderservice.vo.response.order.PreviewOrderResponse;

import java.util.List;

/**
 * @author: Devaraj Reddy,
 * Date : 2019-09-20
 */
public interface OrderService {

    CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest);

    PreviewOrderResponse previewOrder(PreviewOrderRequest previewOrderRequest);

    CreateOrderResponse getOrderById(String orderId);

    List<CreateOrderResponse> getMyOrders();

    List<CreateOrderResponse> getAllOrders();
}