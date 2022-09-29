package com.bookstore.catalogservice.service;

import com.bookstore.catalogservice.vo.request.CreateOrUpdateProducerRequest;

public interface ProducerService {
    Boolean createProducer(CreateOrUpdateProducerRequest createProducerRequest);

    Boolean updateProducer(CreateOrUpdateProducerRequest updateProductRequest);


}
