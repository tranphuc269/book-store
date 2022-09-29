package com.bookstore.catalogservice.controller;


import com.bookstore.catalogservice.service.ProducerService;
import com.bookstore.catalogservice.service.ProductService;
import com.bookstore.catalogservice.service.S3BucketStorageService;
import com.bookstore.catalogservice.vo.request.CreateOrUpdateProducerRequest;
import com.bookstore.catalogservice.vo.resonse.ProductResponse;
import com.bookstore.catalogservice.vo.resonse.producer.ProducerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    @Autowired
    private S3BucketStorageService s3BucketStorageService;

    @Autowired
    private ProductService productService;


    @PostMapping("/producer")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<?> createProducer(
            @RequestParam(name = "producerName") String producerName,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "file") MultipartFile file
    ) {

        String imgUrl = s3BucketStorageService.uploadFileToS3(file);

        CreateOrUpdateProducerRequest
                createOrUpdateProducerRequest = CreateOrUpdateProducerRequest
                .builder()
                .producerName(producerName)
                .description(description)
                .imgUrl(imgUrl)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(producerService
                        .createProducer(createOrUpdateProducerRequest));
    }

    @PutMapping("/producer")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<?> updateProducer(
            @RequestParam(name = "producerName") String producerName,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "producerId") String producerId,
            @RequestParam(name = "file") MultipartFile file
    ) {
        String imgUrl;
        if (file == null) {
            imgUrl = "";
        } else {
            imgUrl = s3BucketStorageService.uploadFileToS3(file);
        }
        CreateOrUpdateProducerRequest
                createOrUpdateProducerRequest = CreateOrUpdateProducerRequest
                .builder()
                .producerId(producerId)
                .producerName(producerName)
                .description(description)
                .imgUrl(imgUrl)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(producerService
                        .createProducer(createOrUpdateProducerRequest));
    }

    @GetMapping("/producer")
    public ResponseEntity<?> getAllProducer() {
        List<ProducerResponse> producerResponses = producerService.getAllProducer();
        return ResponseEntity.ok(producerResponses);
    }

    @GetMapping("/producer/{producerId}")
    public ResponseEntity<?> getDetailProducer(@PathVariable String producerId) {
        ProducerResponse producerResponse = producerService.getProducer(producerId);
        List<ProductResponse> productDAOList = productService.getProductByProducerId(producerId);
        producerResponse.setProducts(productDAOList);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(producerResponse);
    }
}