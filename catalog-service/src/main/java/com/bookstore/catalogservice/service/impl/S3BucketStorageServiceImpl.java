package com.bookstore.catalogservice.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import com.bookstore.catalogservice.service.S3BucketStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class S3BucketStorageServiceImpl implements S3BucketStorageService {

    private static final Logger LOG = LoggerFactory.getLogger(S3BucketStorageServiceImpl.class);

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${application.bucket.name}")
    private String bucketName;

    @Override
    public String uploadFileToS3(MultipartFile file) {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        var metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        amazonS3
                .putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        amazonS3.setObjectAcl(bucketName, fileName, CannedAccessControlList.PublicRead);
        return "https://"+bucketName+".s3.ap-southeast-1.amazonaws.com/" + fileName;
    }

    @Override
    public byte[] downloadFileFromS3(String fileName) {
        S3Object s3Object = amazonS3.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String deleteFileFromS3(String fileName) {
        amazonS3.deleteObject(bucketName, fileName);
        return fileName + " removed ...";
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            LOG.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }
}