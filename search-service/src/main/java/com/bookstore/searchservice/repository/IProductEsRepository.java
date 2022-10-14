package com.bookstore.searchservice.repository;

import com.bookstore.searchservice.domain.ProductEsModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface IProductEsRepository extends ElasticsearchRepository<ProductEsModel, String> {
}
