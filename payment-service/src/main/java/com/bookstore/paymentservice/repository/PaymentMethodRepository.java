package com.bookstore.paymentservice.repository;

import com.bookstore.paymentservice.dao.PaymentMethodDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethodDAO, String> {
}
