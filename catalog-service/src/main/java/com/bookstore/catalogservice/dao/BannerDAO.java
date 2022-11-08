package com.bookstore.catalogservice.dao;


import com.bookstore.catalogservice.common.util.DateAudit;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "banners")
@Builder
public class BannerDAO extends DateAudit implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "banner_id", updatable = false, nullable = false)
    private String bannerId;

    @Column(name = "banner_title", nullable = false, columnDefinition = "TEXT")
    private String bannerName;

    @Column(name = "img_url", columnDefinition = "TEXT")
    private String imgUrl;

    @Column(name = "navigation", columnDefinition = "TEXT")
    private String navigation;
}
