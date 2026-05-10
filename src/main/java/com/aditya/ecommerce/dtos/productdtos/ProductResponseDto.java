package com.aditya.ecommerce.dtos.productdtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductResponseDto {
    private Long Id;
    private String name;
    private String description;
    private double price;
    private String categoryName;
}
