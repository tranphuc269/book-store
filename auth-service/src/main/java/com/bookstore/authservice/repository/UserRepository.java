package com.bookstore.authservice.repository;

import java.util.List;
import java.util.Optional;

import com.bookstore.authservice.dao.UserDAO;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;


@Transactional
public interface UserRepository extends CrudRepository<UserDAO, Long> {

  Optional<UserDAO> findByUserName(String username);

  Optional<UserDAO> findByUserNameOrEmail(String uName, String eMail);

  Optional<UserDAO> findByUserId(String userId);

  Boolean existsByUserName(String userName);

  Boolean existsByEmail(String email);

}