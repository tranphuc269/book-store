package com.bookstore.commonservice.application.controller;

import com.bookstore.commonservice.application.response.dto.BaseResponse;
import com.bookstore.commonservice.application.response.dto.HelloDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping
public class HelloController {
    @GetMapping("/messages/{id}")
    public BaseResponse<HelloDto> sayHelloWorld(@PathVariable String id) {
        var res = HelloDto.builder()
                .id(id)
                .message("Hello, it works!")
                .build();
        return BaseResponse.ofSucceeded(res);
    }
}
