package com.bookstore.authservice.repository;

import com.bookstore.authservice.dao.RoleDAO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<RoleDAO, Long> {

  Optional<RoleDAO> findByRoleName(String name);

  Boolean existsByRoleName(String roleName);

  @Override
  List<RoleDAO> findAll();
}