package com.bookstore.catalogservice.service;

import com.bookstore.catalogservice.vo.request.CreateBannerRequest;
import com.bookstore.catalogservice.vo.resonse.BannerResponse;

import javax.validation.Valid;
import java.util.List;

public interface BannerService {
    BannerResponse createBanner(@Valid CreateBannerRequest createBannerRequest);

    BannerResponse deleteBanner(String bannerId);

    List<BannerResponse> getAllBanner();
}
