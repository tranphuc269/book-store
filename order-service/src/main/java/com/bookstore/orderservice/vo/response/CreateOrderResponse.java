package com.bookstore.orderservice.vo.response;

import com.bookstore.orderservice.dao.OrderBillingAddressDAO;
import com.bookstore.orderservice.dao.OrderItemDAO;
import com.bookstore.orderservice.dao.OrderShippingAddressDAO;
import com.bookstore.orderservice.vo.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderResponse {
    private String orderId;
    private List<OrderItemDAO> orderItemDAOS = new ArrayList<>();
    private OrderShippingAddressDAO shippingAddress;
    private OrderBillingAddressDAO billingAddress;
    private Card card;
    private Double itemsTotalPrice;
    private Double taxPrice;
    private Double shippingPrice;
    private Double totalPrice;
    private boolean isPaid;
    private LocalDateTime paymentDate;
    private boolean isDelivered;
    private String paymentReceiptUrl;
    private LocalDateTime deliveredDate;
    private Instant created_at;
}