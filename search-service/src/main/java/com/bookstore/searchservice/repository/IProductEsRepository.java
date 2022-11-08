package com.bookstore.searchservice.repository;

import com.bookstore.searchservice.domain.ProductEsModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories
public interface IProductEsRepository extends ElasticsearchRepository<ProductEsModel, String> {
}
