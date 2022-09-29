package com.bookstore.catalogservice.service;

import com.bookstore.catalogservice.vo.request.CreateOrUpdateProducerRequest;
import com.bookstore.catalogservice.vo.resonse.producer.ProducerResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProducerService {
    Boolean createProducer(CreateOrUpdateProducerRequest createProducerRequest);

    Boolean updateProducer(CreateOrUpdateProducerRequest updateProductRequest);

    ProducerResponse getProducer(String producerId);

    Page<ProducerResponse> getAllProducerByPage(String sort, Integer page, Integer size);

    List<ProducerResponse> getAllProducer();
}
