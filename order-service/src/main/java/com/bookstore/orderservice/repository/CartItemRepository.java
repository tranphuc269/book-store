package com.bookstore.orderservice.repository;

import com.bookstore.orderservice.dao.CartItemDAO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface CartItemRepository extends CrudRepository<CartItemDAO, String> {

    @Modifying
    @Transactional
    void deleteByCartItemId(String cartItemId);

    Optional<CartItemDAO> findByCartItemId(String cartItemId);
}