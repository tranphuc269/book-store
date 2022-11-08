package com.bookstore.catalogservice.vo.resonse;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@RedisHash()
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BannerResponse {
    private static final long serialVersionUID = 123456654323L;


    @Id
    private String id;
    private String name;
    private String navigation;
    private String imgUrl;
}
