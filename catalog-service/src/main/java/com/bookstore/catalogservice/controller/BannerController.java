package com.bookstore.catalogservice.controller;


import com.bookstore.catalogservice.common.response.CommonResult;
import com.bookstore.catalogservice.repository.BannerRepository;
import com.bookstore.catalogservice.service.BannerService;
import com.bookstore.catalogservice.service.S3BucketStorageService;
import com.bookstore.catalogservice.vo.request.CreateBannerRequest;
import com.bookstore.catalogservice.vo.resonse.BannerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @Autowired
    private S3BucketStorageService s3BucketStorageService;

    @PostMapping("/banner")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public CommonResult<BannerResponse> createBanner(@RequestParam(name = "bannerName") String bannerName,
                                                       @RequestParam(name = "navigation") String navigation,
                                                       @RequestParam(name = "file") MultipartFile file
    ) {
        String imgUrl = s3BucketStorageService.uploadFileToS3(file);
        CreateBannerRequest createCategoryRequest = CreateBannerRequest
                .builder()
                .bannerName(bannerName)
                .navigation(navigation)
                .imgUrl(imgUrl)
                .build();

        BannerResponse bannerResponse = bannerService.createBanner(createCategoryRequest);

        return CommonResult.success(bannerResponse);
    }

    @DeleteMapping("/banner/{bannerId}")
    public CommonResult<BannerResponse> deleteBanner(@PathVariable("bannerId") String bannerId){
        BannerResponse bannerResponse = bannerService.deleteBanner(bannerId);

        return CommonResult.success(bannerResponse);
    }

    @GetMapping("/banners")
    public CommonResult<List<BannerResponse>> createBanner(
    ) {
        List<BannerResponse> banners = bannerService.getAllBanner();
        return CommonResult.success(banners);
    }


}
