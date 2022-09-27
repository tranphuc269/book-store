package com.bookstore.billingservice.vo.response;


import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class GetAddressResponse {
    private String addressId;
    private String userId;
    private String userName;
    private String phoneNumber;
    private String city; // tỉnh thành phố
    private String district; // huyện
    private String province; // xã
    private String detail;
}
