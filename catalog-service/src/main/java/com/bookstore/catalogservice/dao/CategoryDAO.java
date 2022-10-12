package com.bookstore.catalogservice.dao;

import com.bookstore.catalogservice.common.util.DateAudit;
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
import java.io.Serializable;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
@Builder
public class CategoryDAO extends DateAudit implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "category_id", updatable = false, nullable = false)
    private String categoryId;

    @Column(name = "category_name", nullable = false, columnDefinition = "TEXT")
    private String categoryName;

    private String description;
    @Column(name = "img_url", columnDefinition = "TEXT")
    private String imgUrl;
//
//    @Id
//    @AccessType(AccessType.Type.PROPERTY)
//    public String getId() {
//        return categoryId;
//    }
//    public void setId(String categoryId) {
//        this.categoryId = categoryId;
//    }
//    public String getCategoryId() {
//        return categoryId;
//    }
//    public void setCategoryId(String categoryId) {
//        this.categoryId = categoryId;
//    }
}