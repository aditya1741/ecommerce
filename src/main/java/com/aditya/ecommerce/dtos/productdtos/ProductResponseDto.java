package com.aditya.ecommerce.dtos.productdtos;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto implements Serializable {
    private Long Id;
    private String name;
    private String description;
    private double price;
    private String categoryName;
}
