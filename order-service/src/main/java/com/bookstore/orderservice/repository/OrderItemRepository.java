package com.bookstore.orderservice.repository;

import com.bookstore.orderservice.dao.OrderItemDAO;
import org.springframework.data.repository.CrudRepository;


public interface OrderItemRepository extends CrudRepository<OrderItemDAO, String> {
}