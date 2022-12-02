package com.bookstore.orderservice.vo.response.feign;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeignAddressResponse {
    private String addressId;
    private String userId;
    private String userName;
    private String phoneNumber;
    private String city; // tỉnh thành phố
    private String district; // huyện
    private String province; // xã
    private String detail;
}