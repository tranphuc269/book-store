package com.bookstore.searchservice.domain.dao;

import com.bookstore.searchservice.common.DateAudit;
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

    @Column(name = "product_name", nullable = false, columnDefinition = "TEXT")
    private String productName;

    @Column(name = "product_description", columnDefinition = "TEXT")
    private String description;
    private double price;

    @Column(name = "images", columnDefinition = "TEXT")
    private String images;

    @Column(name = "category_id", columnDefinition = "TEXT")
    private String categoryId;

    @Column(name = "producer_id", columnDefinition = "TEXT")
    private String producerId;

    @Column(name = "available_item_count")
    private int availableItemCount;


    public List<String> getListImages() {
        List<String> imgs = new ArrayList<>(List.of(this.getImages().replace(']',
                ' ').replace('[',
                ' ').trim().split(",")));
        imgs.replaceAll(String::trim);
        return imgs;
    }
}
