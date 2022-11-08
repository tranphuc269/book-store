package com.bookstore.catalogservice.repository;

import com.bookstore.catalogservice.dao.BannerDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<BannerDAO, String> {

}
