package com.aditya.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(schema = "products")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String Description;
    private double price;
    private int stock;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name ="category_id")
    private Category category;

}
