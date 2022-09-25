package com.bookstore.authservice.service;


import com.bookstore.authservice.vo.request.MapRoleToUsersRequest;
import com.bookstore.authservice.vo.request.MapUserToRolesRequest;

public interface UserRoleService {

  void mapUserToRoles(String userNameOrEmail, MapUserToRolesRequest mapUserToRolesRequest);

  void removeRolesFromUser(String userNameOrEmail, MapUserToRolesRequest mapUserToRolesRequest);

  void mapRoleToUsers(String roleName, MapRoleToUsersRequest mapRoleToUsersRequest);
}