package com.bookstore.orderservice.repository;

import com.bookstore.orderservice.dao.OrderDAO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface OrderRepository extends CrudRepository<OrderDAO, String> {

    OrderDAO findByOrderId(String orderId);

    List<OrderDAO> findByUserId(String userId);
}