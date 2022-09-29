package com.bookstore.catalogservice.controller;


import com.bookstore.catalogservice.dao.ProducerDAO;
import com.bookstore.catalogservice.dao.ProductDAO;
import com.bookstore.catalogservice.service.ProducerService;
import com.bookstore.catalogservice.service.S3BucketStorageService;
import com.bookstore.catalogservice.vo.request.CreateOrUpdateProducerRequest;
import com.bookstore.catalogservice.vo.resonse.producer.ProducerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController("/producer")
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    @Autowired
    private S3BucketStorageService s3BucketStorageService;


    @PostMapping("")
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

    @PutMapping("")
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

    @GetMapping("/{producerId}")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<?> getDetailProducer(@PathVariable String producerId) {
        ProducerResponse producerResponse = producerService.getProducer(producerId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(producerResponse);
    }

}
