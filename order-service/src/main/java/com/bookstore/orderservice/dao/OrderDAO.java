package com.bookstore.orderservice.dao;

import com.bookstore.orderservice.common.util.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDAO extends DateAudit {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "order_id", updatable = false, nullable = false)
    private String orderId;
    
    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_id", nullable = false)
    private String userId;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemDAO> orderItemDAOS = new ArrayList<>();

    @Column(name = "total_item_price", nullable = false)
    private double totalItemsPrice;

    @Column(name = "total_order_price", nullable = false)
    private double totalOrderPrice;

    @Column(name = "payment_method_id", nullable = false)
    private String paymentMethodId;

    @Column(name = "tax_price", nullable = false)
    private double taxPrice;

    @Column(name = "shipping_price", nullable = false)
    private double shippingPrice;

    @Column(name = "id_paid")
    private boolean isPaid;

    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "payment_receipt_url")
    private String paymentReceiptUrl;

    @Column(name = "delivered_date")
    private LocalDateTime deliveredDate;

    @Column(name = "id_delivered")
    private boolean isDelivered;

}