package com.bookstore.orderservice.repository;

import com.bookstore.orderservice.dao.OrderBillingAddressDAO;
import org.springframework.data.repository.CrudRepository;

public interface OrderBillingAddressRepository extends CrudRepository<OrderBillingAddressDAO, String> {
    OrderBillingAddressDAO findByOrderId(String orderId);
}