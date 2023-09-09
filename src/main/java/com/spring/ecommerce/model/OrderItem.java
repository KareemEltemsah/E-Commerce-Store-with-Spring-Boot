package com.spring.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
