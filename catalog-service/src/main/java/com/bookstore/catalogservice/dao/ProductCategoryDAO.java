package com.bookstore.catalogservice.dao;

import com.bookstore.catalogservice.common.util.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_category")
@Builder
public class ProductCategoryDAO extends DateAudit {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "product_category_id", updatable = false, nullable = false)
    private String productCategoryId;

    @Column(name = "product_category_name", nullable = false)
    private String productCategoryName;

    @OneToMany(
            mappedBy = "productCategory",
            cascade = CascadeType.ALL
    )
    private List<ProductDAO> products;
    private String description;
}