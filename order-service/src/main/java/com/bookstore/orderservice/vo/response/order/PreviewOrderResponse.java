package com.bookstore.orderservice.vo.response.order;

import com.bookstore.orderservice.dao.OrderItemDAO;
import com.bookstore.orderservice.vo.response.feign.FeignAddressResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PreviewOrderResponse {
    private List<OrderItemDAO> orderItems = new ArrayList<>();
    private FeignAddressResponse shippingAddress;
    private FeignAddressResponse billingAddress;
    private Double itemsTotalPrice;
    private Double taxPrice;
    private Double shippingPrice;
    private Double totalPrice;
}