package com.bookstore.authservice.service;

import com.bookstore.authservice.vo.request.CreateOAuthClientRequest;
import com.bookstore.authservice.vo.request.SignInRequest;
import com.bookstore.authservice.vo.request.SignUpRequest;
import com.bookstore.authservice.vo.response.CreateOAuthClientResponse;
import com.bookstore.authservice.vo.response.CreateUserResponse;
import com.bookstore.authservice.vo.response.GetUserInfoResponse;
import com.bookstore.authservice.vo.response.JwtAuthenticationResponse;


public interface AuthService {

  CreateOAuthClientResponse createOAuthClient(CreateOAuthClientRequest createOAuthClientRequest);

  CreateUserResponse registerUser(SignUpRequest signUpRequest);

  JwtAuthenticationResponse loginUser(SignInRequest signInRequest);
}