package com.bookstore.catalogservice.service.impl;

import com.bookstore.catalogservice.dao.ProducerDAO;
import com.bookstore.catalogservice.repository.ProducerRepository;
import com.bookstore.catalogservice.service.ProducerService;
import com.bookstore.catalogservice.vo.request.CreateOrUpdateProducerRequest;
import com.bookstore.catalogservice.vo.resonse.producer.ProducerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


@Service
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
            ProducerDAO oldProductDAO = (producerRepository.findById(updateProductRequest
                    .getProducerId()))
                    .orElseThrow(() -> new RuntimeException("ProducerId doesn't exits"));
            ProducerDAO producerDAO = ProducerDAO
                    .builder()
                    .producerId(updateProductRequest.getProducerId())
                    .producerName(updateProductRequest.getProducerName())
                    .description(updateProductRequest.getDescription())
                    .imgUrl(updateProductRequest.getImgUrl().isEmpty()
                            ? oldProductDAO.getImgUrl()
                            : updateProductRequest.getImgUrl())
                    .build();
            producerRepository.save(producerDAO);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ProducerResponse getProducer(String producerId) {
        ProducerDAO producerDAO = (producerRepository.findById(producerId)).orElseThrow(() -> new RuntimeException("Producer doesn't exits"));
        return ProducerResponse
                .builder()
                .producerId(producerDAO.getProducerId())
                .producerName(producerDAO.getProducerName())
                .description(producerDAO.getDescription())
                .img(producerDAO.getImgUrl())
                .build();
    }

    @Override
    public Page<ProducerResponse> getAllProducerByPage(String sort, Integer page, Integer size) {
        if (size == null || size == 0) {
            size = 20;
        }

        //set defaults
        if (page == null || page == 0) {
            page = 0;
        }

        Pageable pageable;

        if (sort == null) {
            pageable = PageRequest.of(page, size);
        } else {
            Sort.Order order;

            try {
                String[] split = sort.split(",");

                Sort.Direction sortDirection = Sort.Direction.fromString(split[1]);
                order = new Sort.Order(sortDirection, split[0]).ignoreCase();
                pageable = PageRequest.of(page, size, Sort.by(order));

            } catch (Exception e) {
                throw new RuntimeException("Not a valid sort value, It should be 'fieldName,direction', example : 'category,asc");
            }

        }
        Page<ProducerDAO> producerDAOList = producerRepository.findAll(pageable);
        Page<ProducerResponse> producerResponsePage = producerDAOList.map(new Function<ProducerDAO, ProducerResponse>() {
            @Override
            public ProducerResponse apply(ProducerDAO producerDAO) {
                return ProducerResponse
                        .builder()
                        .producerId(producerDAO.getProducerId())
                        .producerName(producerDAO.getProducerName())
                        .description(producerDAO.getDescription())
                        .img(producerDAO.getImgUrl())
                        .build();
            }
        });

        return producerResponsePage;
    }

    @Override
    public List<ProducerResponse> getAllProducer() {
        List<ProducerDAO> producerDAOList = producerRepository.findAll();
        List<ProducerResponse> producerResponses = new ArrayList<>();
        producerDAOList.forEach(producerDAO -> {
            producerResponses.add(ProducerResponse
                    .builder()
                    .producerId(producerDAO.getProducerId())
                    .producerName(producerDAO.getProducerName())
                    .description(producerDAO.getDescription())
                    .img(producerDAO.getImgUrl())
                    .build());
        });
        return producerResponses;
    }
}
