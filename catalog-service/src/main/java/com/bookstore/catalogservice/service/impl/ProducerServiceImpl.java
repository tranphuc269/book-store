package com.bookstore.catalogservice.service.impl;

import com.bookstore.catalogservice.dao.ProducerDAO;
import com.bookstore.catalogservice.repository.ProducerRepository;
import com.bookstore.catalogservice.service.ProducerService;
import com.bookstore.catalogservice.vo.request.CreateOrUpdateProducerRequest;
import org.springframework.beans.factory.annotation.Autowired;

public class ProducerServiceImpl implements ProducerService {

    @Autowired
    private ProducerRepository producerRepository;

    @Override
    public Boolean createProducer(CreateOrUpdateProducerRequest createProducerRequest) {
        try {
            ProducerDAO producerDAO = ProducerDAO
                    .builder()
                    .producerName(createProducerRequest.getProducerName())
                    .description(createProducerRequest.getDescription())
                    .imgUrl(createProducerRequest.getImgUrl())
                    .build();
            producerRepository.save(producerDAO);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean updateProducer(CreateOrUpdateProducerRequest updateProductRequest) {
        try {
            (producerRepository.findById(updateProductRequest
                    .getProducerId()))
                    .orElseThrow(() -> new RuntimeException("ProducerId not found"));
            ProducerDAO producerDAO = ProducerDAO
                    .builder()
                    .producerId(updateProductRequest.getProducerId())
                    .producerName(updateProductRequest.getProducerName())
                    .description(updateProductRequest.getDescription())
                    .imgUrl(updateProductRequest.getImgUrl())
                    .build();
            producerRepository.save(producerDAO);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
