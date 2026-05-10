package com.aditya.ecommerce.dtos.categorydtos;

import com.aditya.ecommerce.dtos.productdtos.ProductResponseDto;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CategoryResponseDto {
    private Long Id;
    private String name;

}
