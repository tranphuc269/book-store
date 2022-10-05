package com.bookstore.orderservice.vo.response.feign;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUserResponse {

    private String userId;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

}