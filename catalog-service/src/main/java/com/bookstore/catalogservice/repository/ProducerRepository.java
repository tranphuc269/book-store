package com.bookstore.catalogservice.repository;

import com.bookstore.catalogservice.dao.ProducerDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<ProducerDAO, String> {
}
