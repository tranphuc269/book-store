package com.bookstore.catalogservice.controller;

import com.bookstore.catalogservice.service.S3BucketStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileOperationController {

  @Autowired
  private S3BucketStorageService s3BucketStorageService;

  @GetMapping
  public String viewHomePage() {
    return "upload";
  }

  @PostMapping("/upload")
  public String uploadFile(@RequestParam(value = "file") MultipartFile file, Model model) {
    String responseMessage = s3BucketStorageService.uploadFileToS3(file);
    return responseMessage;
  }

  @GetMapping("/download/{fileName}")
  public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
    byte[] data = s3BucketStorageService.downloadFileFromS3(fileName);
    ByteArrayResource resource = new ByteArrayResource(data);
    return ResponseEntity
        .ok()
        .contentLength(data.length)
        .header("Content-type", "application/octet-stream")
        .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
        .body(resource);
  }

  @DeleteMapping("/delete/{fileName}")
  public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
    return new ResponseEntity<>(s3BucketStorageService.deleteFileFromS3(fileName), HttpStatus.OK);
  }
}