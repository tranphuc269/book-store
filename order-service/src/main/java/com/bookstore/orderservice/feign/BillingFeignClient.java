package com.bookstore.orderservice.feign;

import com.bookstore.orderservice.common.response.CommonResult;
import com.bookstore.orderservice.vo.response.feign.FeignAddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient("billing-service")
public interface BillingFeignClient {

    @GetMapping("/address/{addressId}")
    CommonResult<FeignAddressResponse> getAddressById(@PathVariable("addressId") String addressId);

}