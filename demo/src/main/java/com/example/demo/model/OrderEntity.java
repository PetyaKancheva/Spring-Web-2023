package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class OrderEntity extends BaseEntity {
    private String name;
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User buyer;
    @OneToMany
    private List<LineItem> lineItems;


}
