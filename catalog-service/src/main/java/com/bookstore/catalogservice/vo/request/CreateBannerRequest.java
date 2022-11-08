package com.bookstore.catalogservice.vo.request;


import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;

@Builder
@Data
public class CreateBannerRequest {
    String imgUrl;
    String bannerName;
    String navigation; /// CATEGORY, BRAND
}
