package com.bookstore.catalogservice.dao;

import com.bookstore.catalogservice.common.util.DateAudit;
import com.bookstore.catalogservice.vo.resonse.ProductResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "products")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDAO extends DateAudit {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "product_id", updatable = false, nullable = false)
    private String productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_description", columnDefinition = "TEXT")
    private String description;
    private double price;

    @Column(name = "images")
    private String images;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryDAO category;

    @Column(name = "available_item_count")
    private int availableItemCount;

    public String getCategory() {
        return category.getCategoryName();
    }

    public static ProductResponse fromEntity(ProductDAO product) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return ProductResponse
                .builder()
                .productId(product.getProductId())
                .images(product.getListImages())
                .price(product.getPrice())
                .availableItemCount(product.getAvailableItemCount())
                .productName(product.getProductName())
                .description(product.getDescription())
                .averageRating(0.0)
                .categoryName(product.getCategory())
                .build();
    }

    public List<String> getListImages() {
        List<String> imgs = new ArrayList<>(List.of(this.getImages().replace(']', ' ').replace('[', ' ').trim().split(",")));
        imgs.replaceAll(String::trim);
        return imgs;
    }
}