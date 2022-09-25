package com.bookstore.authservice.service.impl;

import java.util.List;

import com.bookstore.authservice.dao.RoleDAO;
import com.bookstore.authservice.repository.RoleRepository;
import com.bookstore.authservice.service.RoleService;
import com.bookstore.authservice.vo.request.CreateRoleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {


  private final RoleRepository roleRepository;

  @Autowired
  public RoleServiceImpl(RoleRepository roleRepository){
    this.roleRepository = roleRepository;
  }

  @Override
  public String createRole(CreateRoleRequest createRoleRequest) {

    RoleDAO role = RoleDAO.builder()
        .roleName(createRoleRequest.getRoleName())
        .roleDescription(createRoleRequest.getRoleDescription())
        .build();

    RoleDAO savedRole = roleRepository.save(role);
    return savedRole.getId();
  }

  @Override
  public List<RoleDAO> getAllRoles() {
    List<RoleDAO> allRoles = roleRepository.findAll();
    return allRoles;
  }
}

