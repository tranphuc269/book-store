package com.bookstore.billingservice.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAddressRequest {

    @NotBlank
    private String addressId;

    @NonNull
    private String userName;
    private String phoneNumber;

    @NonNull
    private String city; // tỉnh thành phố

    @NonNull
    private String district; // huyện

    @NonNull
    private String province; // xã

    @NonNull
    private String detail;

}