package com.bookstore.billingservice.service;

import com.bookstore.billingservice.vo.request.CreateAddressRequest;
import com.bookstore.billingservice.vo.request.UpdateAddressRequest;
import com.bookstore.billingservice.vo.response.GetAddressResponse;

import java.util.List;


public interface AddressService {

    void createAddress(CreateAddressRequest createAddressRequest);

    List<GetAddressResponse> getAddress();

    void updateAddress(UpdateAddressRequest updateAddressRequest);

    GetAddressResponse getAddressById(String addressId);

    void deleteAddressById(String addressId);
}