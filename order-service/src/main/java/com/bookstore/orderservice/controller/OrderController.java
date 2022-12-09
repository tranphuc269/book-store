package com.bookstore.orderservice.controller;

import com.bookstore.orderservice.common.response.CommonResult;
import com.bookstore.orderservice.service.OrderService;
import com.bookstore.orderservice.vo.request.CreateOrderRequest;
import com.bookstore.orderservice.vo.request.PreviewOrderRequest;
import com.bookstore.orderservice.vo.response.order.CreateOrderResponse;
import com.bookstore.orderservice.vo.response.order.PreviewOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
public class OrderController {
    
    @Autowired
    OrderService orderService;
    
    @PostMapping("/order")
    public CommonResult<CreateOrderResponse> createOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest) {

        CreateOrderResponse createOrderResponse = orderService.createOrder(createOrderRequest);
        return CommonResult.success(createOrderResponse);
    }

    @GetMapping("/order/{orderId}")
    public CommonResult<CreateOrderResponse> getOrderById(@PathVariable("orderId") String orderId) {

        CreateOrderResponse createOrderResponse = orderService.getOrderById(orderId);
        return CommonResult.success(createOrderResponse);
    }

    @GetMapping("/order/myorders")
    public CommonResult<List<CreateOrderResponse>> getMyOrders() {

        List<CreateOrderResponse> createOrderResponse = orderService.getMyOrders();
        return CommonResult.success(createOrderResponse);
    }

    @GetMapping("/orders")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public CommonResult<List<CreateOrderResponse>> getAllOrders() {
        List<CreateOrderResponse> createOrderResponse = orderService.getAllOrders();
        return CommonResult.success(createOrderResponse);
    }

    @PostMapping("/previewOrder")
    public CommonResult<PreviewOrderResponse> previewOrder(@RequestBody @Valid PreviewOrderRequest previewOrderRequest) {

        PreviewOrderResponse previewOrderResponse = orderService.previewOrder(previewOrderRequest);

        return CommonResult.success(previewOrderResponse);
    }
}