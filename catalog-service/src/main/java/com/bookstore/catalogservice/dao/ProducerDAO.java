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
@Table(name = "producers")
@Builder
public class ProducerDAO extends DateAudit {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "producer_id", updatable = false, nullable = false)
    private String producerId;

    @Column(name = "producer_name", nullable = false, columnDefinition = "TEXT")
    private String producerName;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(name = "img_url", columnDefinition = "TEXT")
    private String imgUrl;
}
