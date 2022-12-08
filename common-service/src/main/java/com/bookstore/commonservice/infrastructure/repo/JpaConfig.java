package com.bookstore.commonservice.infrastructure.repo;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.phuc.*"})
@EntityScan(basePackages = {"com.phuc.*"})
public class JpaConfig {
}
