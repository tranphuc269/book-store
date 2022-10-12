package com.bookstore.catalogservice.vo.resonse;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@Builder
@RedisHash()
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse implements Serializable {

    private static final long serialVersionUID = 123456654322L;


    @Id
    private String id;
    private String name;
    private String imgUrl;
    private String description;
}
