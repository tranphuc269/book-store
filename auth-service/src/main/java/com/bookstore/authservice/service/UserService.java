package com.bookstore.authservice.service;

import com.bookstore.authservice.vo.request.CreateUserRequest;
import com.bookstore.authservice.vo.request.UpdateUserRequest;
import com.bookstore.authservice.vo.request.UpdateUserRequestFromAdmin;
import com.bookstore.authservice.vo.response.GetUserInfoResponse;
import com.bookstore.authservice.vo.response.GetUserResponse;

import java.util.List;


public interface UserService {

  String createUser(CreateUserRequest createUserRequest);

  GetUserResponse getUserByUserName(String userName);

  GetUserResponse getUserByUserId(String userId);

  GetUserInfoResponse getUserInfo();

  void updateUserInfo(UpdateUserRequest updateUserRequest);

  void deleteUserById(String userId);

  List<GetUserResponse> getAllUsers();

  void updateUser(String userId, UpdateUserRequestFromAdmin updateUserRequestFromAdmin);
}