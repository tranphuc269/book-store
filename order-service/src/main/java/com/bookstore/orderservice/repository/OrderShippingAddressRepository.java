package com.bookstore.orderservice.repository;

import com.bookstore.orderservice.dao.OrderShippingAddressDAO;
import org.springframework.data.repository.CrudRepository;


public interface OrderShippingAddressRepository extends CrudRepository<OrderShippingAddressDAO, String> {
    OrderShippingAddressDAO findByOrderId(String orderId);
}