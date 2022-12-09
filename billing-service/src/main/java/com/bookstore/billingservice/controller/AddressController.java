package com.bookstore.billingservice.controller;


import com.bookstore.billingservice.common.response.CommonResult;
import com.bookstore.billingservice.service.AddressService;
import com.bookstore.billingservice.vo.request.CreateAddressRequest;
import com.bookstore.billingservice.vo.request.UpdateAddressRequest;
import com.bookstore.billingservice.vo.response.GetAddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class AddressController {
    
    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/address")
    public ResponseEntity<Object> createAddress(@RequestBody CreateAddressRequest createAddressRequest) {
        addressService.createAddress(createAddressRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/address")
    public ResponseEntity<Object> updateAddress(@RequestBody UpdateAddressRequest updateAddressRequest) {
        addressService.updateAddress(updateAddressRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/address")
    public CommonResult<List<GetAddressResponse>> getAddress() {
        List<GetAddressResponse> address = addressService.getAddress();
        return CommonResult.success(address);
    }

    @GetMapping("/address/{addressId}")
    public CommonResult<GetAddressResponse> getAddressById(@PathVariable("addressId") String addressId) {
        GetAddressResponse address = addressService.getAddressById(addressId);
        return CommonResult.success(address);
    }

    @DeleteMapping("/address/{addressId}")
    public ResponseEntity<?> deleteAddressById(@PathVariable("addressId") String addressId) {
        addressService.deleteAddressById(addressId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
}

