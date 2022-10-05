package com.bookstore.paymentservice.dao;


import lombok.*;

import javax.persistence.*;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment_methods")
@Builder
public class PaymentMethodDAO {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "payment_id", updatable = false, nullable = false)
    private String paymentId;

    @Column(name = "payment_name", nullable = false, columnDefinition = "TEXT", unique = true)
    private String paymentName;

    @Column(name = "payment_description", columnDefinition = "TEXT")
    private String paymentDescription;

    @Column(name = "payment_type")
    private String paymentType;
}
