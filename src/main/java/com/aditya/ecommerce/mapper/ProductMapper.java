package com.aditya.ecommerce.mapper;

import com.aditya.ecommerce.dtos.productdtos.ProductResponseDto;
import com.aditya.ecommerce.entity.Product;

public class ProductMapper {

    public static ProductResponseDto toResponseDto( Product product){
        return ProductResponseDto
                .builder()
                .Id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .categoryName(product.getCategory().getName())
                .build();

    }
}
