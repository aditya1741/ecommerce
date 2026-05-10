package com.aditya.ecommerce.service;

import com.aditya.ecommerce.dtos.productdtos.ProductRequestDto;
import com.aditya.ecommerce.dtos.productdtos.ProductResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductService {
    ProductResponseDto create(ProductRequestDto productReq);
    Page<ProductResponseDto> getAll(int page, int size);
    ProductResponseDto getById(Long id);
    List<ProductResponseDto> getProductsByCategory(Long categoryId);
    Page<ProductResponseDto> searchProductByName(String keyword, int pageNum, int pageSize);
}
