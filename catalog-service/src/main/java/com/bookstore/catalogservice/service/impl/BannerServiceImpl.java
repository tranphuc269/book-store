package com.bookstore.catalogservice.service.impl;

import com.bookstore.catalogservice.dao.BannerDAO;
import com.bookstore.catalogservice.repository.BannerRepository;
import com.bookstore.catalogservice.service.BannerService;
import com.bookstore.catalogservice.utils.ObjectStatus;
import com.bookstore.catalogservice.vo.request.CreateBannerRequest;
import com.bookstore.catalogservice.vo.resonse.BannerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerRepository bannerRepository;

    @Override
    public BannerResponse createBanner(CreateBannerRequest createBannerRequest) {
        BannerDAO bannerDAO = BannerDAO
                .builder()
                .bannerName(createBannerRequest.getBannerName())
                .imgUrl(createBannerRequest.getImgUrl())
                .navigation(createBannerRequest.getNavigation())
                .build();
        bannerDAO.setStatus(ObjectStatus.ACTIVE.name());
        System.out.println("bannerDAO + " + bannerDAO);
        BannerDAO bannerCreate = bannerRepository.save(bannerDAO);
        return BannerResponse
                .builder()
                .name(bannerCreate.getBannerName())
                .id(bannerCreate.getBannerId())
                .imgUrl(bannerCreate.getImgUrl())
                .navigation(bannerCreate.getNavigation())
                .build();
    }

    @Override
    public BannerResponse deleteBanner(String bannerId) {
        BannerDAO bannerDAO = bannerRepository.findById(bannerId).get();
        bannerDAO.setStatus(ObjectStatus.INACTIVE.name());
        bannerRepository.save(bannerDAO);
        return BannerResponse
                .builder()
                .name(bannerDAO.getBannerName())
                .id(bannerDAO.getBannerId())
                .imgUrl(bannerDAO.getImgUrl())
                .navigation(bannerDAO.getNavigation())
                .build();
    }

    @Override
    public List<BannerResponse> getAllBanner() {
        Iterable<BannerDAO> bannerDAOS = bannerRepository.findAll();
        List<BannerResponse> banners = new ArrayList<>();
        bannerDAOS.forEach(v->{
            banners.add(BannerResponse
                    .builder()
                    .name(v.getBannerName())
                    .id(v.getBannerId())
                    .imgUrl(v.getImgUrl())
                    .navigation(v.getNavigation())
                    .build());
        });
        return banners;
    }
}
