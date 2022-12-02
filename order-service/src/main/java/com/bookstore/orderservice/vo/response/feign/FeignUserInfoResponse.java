package com.bookstore.orderservice.vo.response.feign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeignUserInfoResponse {

  private String userId;
  private String userName;
  private String firstName;
  private String lastName;
  private String email;

}