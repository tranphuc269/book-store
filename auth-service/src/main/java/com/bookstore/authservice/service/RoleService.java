package com.bookstore.authservice.service;

import com.bookstore.authservice.dao.RoleDAO;
import com.bookstore.authservice.vo.request.CreateRoleRequest;

import java.util.List;

public interface RoleService {

  String createRole(CreateRoleRequest createRoleRequest);

  List<RoleDAO> getAllRoles();
}