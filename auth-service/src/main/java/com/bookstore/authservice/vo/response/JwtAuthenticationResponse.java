package com.bookstore.authservice.vo.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class JwtAuthenticationResponse {

  private String access_token;
  private String token_type = "Bearer";
  private String refresh_token;
  private Long expires_in;
}