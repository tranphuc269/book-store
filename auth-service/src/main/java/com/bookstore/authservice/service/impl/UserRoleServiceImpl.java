package com.bookstore.authservice.service.impl;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.bookstore.authservice.common.exception.ErrorResponse;
import com.bookstore.authservice.common.exception.Error;
import com.bookstore.authservice.common.exception.RunTimeExceptionPlaceHolder;
import com.bookstore.authservice.dao.RoleDAO;
import com.bookstore.authservice.dao.UserDAO;
import com.bookstore.authservice.exception.SuccessCodeWithErrorResponse;
import com.bookstore.authservice.repository.RoleRepository;
import com.bookstore.authservice.repository.UserRepository;
import com.bookstore.authservice.service.UserRoleService;
import com.bookstore.authservice.vo.request.MapRoleToUsersRequest;
import com.bookstore.authservice.vo.request.MapUserToRolesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Devaraj Reddy, Date : 2019-07-01
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Override
  public void mapUserToRoles(String userNameOrEmail, MapUserToRolesRequest mapUserToRolesRequest) {

    Optional<UserDAO> userNameOrEmailOptional = userRepository
        .findByUserNameOrEmail(userNameOrEmail, userNameOrEmail);

    UserDAO user = userNameOrEmailOptional.orElseThrow(() ->
        new RunTimeExceptionPlaceHolder("UserNameOrEmail doesn't exist!!")
    );

    Set<RoleDAO> roles = user.getRoles();

    ErrorResponse errorResponse = ErrorResponse.builder()
        .uuid(UUID.randomUUID())
        .errors(new ArrayList<>())
        .build();

    mapUserToRolesRequest.getRoleNames().forEach(roleName -> {

      roleRepository.findByRoleName(roleName).<Runnable>map(role -> () -> roles.add(role))
          .orElse(() -> {
            Error error = Error.builder()
                .code("400")
                .message(roleName + " role doesn't exist!!")
                .build();
            errorResponse.getErrors().add(error);
          })
          .run();
    });

    user.setRoles(roles);

    userRepository.save(user);

    if (!errorResponse.getErrors().isEmpty()) {
      throw new SuccessCodeWithErrorResponse(errorResponse);
    }

  }

  @Override
  public void removeRolesFromUser(String userNameOrEmail, MapUserToRolesRequest mapUserToRolesRequest) {

    Optional<UserDAO> userNameOrEmailOptional = userRepository
            .findByUserNameOrEmail(userNameOrEmail, userNameOrEmail);

    UserDAO user = userNameOrEmailOptional.orElseThrow(() ->
            new RunTimeExceptionPlaceHolder("UserNameOrEmail doesn't exist!!")
    );

    Set<RoleDAO> roles = user.getRoles();

    ErrorResponse errorResponse = ErrorResponse.builder()
            .uuid(UUID.randomUUID())
            .errors(new ArrayList<>())
            .build();

    mapUserToRolesRequest.getRoleNames().forEach(roleName -> {
      //if role exists add to list and persist, else add to error response persist valid roles and send
      // response containing invalid roles.
      roleRepository.findByRoleName(roleName).<Runnable>map(role -> () -> roles.remove(role))
              .orElse(() -> {
                Error error = Error.builder()
                        .code("400")
                        .message(roleName + " role doesn't exist!!")
                        .build();
                errorResponse.getErrors().add(error);
              })
              .run();
    });

    user.setRoles(roles);

    userRepository.save(user);

    if (!errorResponse.getErrors().isEmpty()) {
      throw new SuccessCodeWithErrorResponse(errorResponse);
    }
  }

  @Override
  public void mapRoleToUsers(String roleName, MapRoleToUsersRequest mapRoleToUsersRequest) {

    RoleDAO role = roleRepository.findByRoleName(roleName)
        .orElseThrow(() -> new RuntimeException("Role doesn't exist!!"));

    ErrorResponse errorResponse = ErrorResponse.builder()
        .uuid(UUID.randomUUID())
        .errors(new ArrayList<>())
        .build();

    mapRoleToUsersRequest.getUserNames().forEach(userName -> {
      userRepository.findByUserName(userName).<Runnable>map(user -> () -> role.addUser(user))
          .orElse(() -> {
            Error error = Error.builder()
                .code("400")
                .message(userName + " userName doesn't exist!!")
                .build();
            errorResponse.getErrors().add(error);
          })
          .run();
    });

    roleRepository.save(role);

    if (!errorResponse.getErrors().isEmpty()) {
      throw new SuccessCodeWithErrorResponse(errorResponse);
    }
  }
}