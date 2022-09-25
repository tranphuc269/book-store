package com.bookstore.authservice.repository;

import com.bookstore.authservice.dao.OAuthClient;
import org.springframework.data.repository.CrudRepository;

public interface OAuthClientRepository extends CrudRepository<OAuthClient, Long> {

}