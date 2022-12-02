package com.bookstore.orderservice.feign;

import com.bookstore.orderservice.vo.response.feign.FeignUserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("account-service")
public interface AccountFeignClient {
    @GetMapping("/user")
    FeignUserInfoResponse getUserByUserName(@RequestParam("userName") String userName);

    @GetMapping("/user")
    FeignUserInfoResponse getUserById(@RequestParam("userId") String userId);

    @GetMapping("/userInfo")
    FeignUserInfoResponse getUserInfo();
}