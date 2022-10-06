package com.bookstore.orderservice.vo.request;

import com.bookstore.orderservice.utils.feign.PaymentEnums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderRequest {
    private String billingAddressId;
    @NotBlank
    private String shippingAddressId;
    private String information;
    private PaymentEnums paymentType;
}