package com.bookstore.catalogservice.service;

import com.bookstore.catalogservice.dao.CategoryDAO;
import com.bookstore.catalogservice.vo.request.CreateOrUpdateProducerRequest;
import com.bookstore.catalogservice.vo.resonse.producer.ProducerPagedResponse;
import com.bookstore.catalogservice.vo.resonse.producer.ProducerResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProducerService {
    Boolean createProducer(CreateOrUpdateProducerRequest createProducerRequest);

    Boolean updateProducer(CreateOrUpdateProducerRequest updateProductRequest);

    ProducerResponse getProducer(String producerId);

    Page<ProducerResponse> getAllProducer(String sort, Integer page, Integer size);
}
