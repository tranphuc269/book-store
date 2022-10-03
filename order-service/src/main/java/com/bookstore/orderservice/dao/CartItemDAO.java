package com.bookstore.orderservice.dao;

import com.bookstore.orderservice.common.util.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;


@Entity
@Table(name = "cart_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDAO extends DateAudit {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "cart_item_id", updatable = false, nullable = false)
    private String cartItemId;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "item_price", nullable = false)
    private double itemPrice;

    @Column(name = "extended_price", nullable = false)
    private double extendedPrice;
    
    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name = "product_name", nullable = false)
    private String productName;
    
    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private CartDAO cart;
    
    @PreRemove
    public void dismissParent() {
        this.cart.dismissChild(this); //SYNCHRONIZING THE OTHER SIDE OF RELATIONSHIP
        this.cart = null;
    }
    
    @Override
    public String toString() {
        return "CartItem{" +
                       "cartItemId='" + cartItemId + '\'' +
                       ", quantity=" + quantity +
                       ", itemPrice=" + itemPrice +
                       ", productId='" + productId + '\'' +
                       '}';
    }
}