package com.bookstore.authservice.controllers;

import javax.validation.Valid;

import com.bookstore.authservice.service.UserRoleService;
import com.bookstore.authservice.vo.request.MapRoleToUsersRequest;
import com.bookstore.authservice.vo.request.MapUserToRolesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserRoleController {


    private UserRoleService userRoleService;

    @Autowired
    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping("/user/{userNameOrEmail}/roles")
    public void mapUserToRoles(@PathVariable("userNameOrEmail") String userNameOrEmail,
                               @RequestBody @Valid MapUserToRolesRequest mapUserToRolesRequest) {

        userRoleService.mapUserToRoles(userNameOrEmail, mapUserToRolesRequest);

    }

    @PostMapping("/role/{roleName}/users")
    public void mapRoleToUsers(@PathVariable("roleName") String roleName,
                               @RequestBody @Valid MapRoleToUsersRequest mapRoleToUsersRequest) {

        userRoleService.mapRoleToUsers(roleName, mapRoleToUsersRequest);

    }
}