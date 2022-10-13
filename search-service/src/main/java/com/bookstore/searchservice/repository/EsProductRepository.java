package com.bookstore.searchservice.repository;

import com.bookstore.searchservice.dao.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsProductRepository extends ElasticsearchRepository<EsProduct, String> {
    Page<EsProduct> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keywords);
}
