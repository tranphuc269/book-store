package com.bookstore.orderservice.repository;

import com.bookstore.orderservice.dao.CartDAO;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;


@Transactional
public interface CartRepository extends CrudRepository<CartDAO, String> {

    Optional<CartDAO> findCartByUserId(String userId);

    Optional<CartDAO> findByCartId(String cartId);
}