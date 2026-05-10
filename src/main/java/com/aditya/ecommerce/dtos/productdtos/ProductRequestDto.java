package com.aditya.ecommerce.dtos.productdtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
public class ProductRequestDto {
    private String name;
    private String description;
    @Positive
    private double price;
    @Positive
    private int stock;

    private Long categoryId;

}
