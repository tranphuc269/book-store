package com.bookstore.orderservice.dao;

import com.bookstore.orderservice.common.util.DateAudit;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "order_shipping_address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderShippingAddressDAO extends DateAudit {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "address_id", updatable = false, nullable = false)
    private String addressId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "order_id", updatable = false, nullable = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String orderId;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "province", nullable = false)
    private String province;

    @Column(name = "detail")
    private String detail;
}