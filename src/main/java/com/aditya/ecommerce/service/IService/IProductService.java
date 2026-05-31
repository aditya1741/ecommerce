package com.aditya.ecommerce.service.IService;

import com.aditya.ecommerce.dtos.productdtos.ProductRequestDto;
import com.aditya.ecommerce.dtos.productdtos.ProductResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductService {
    ProductResponseDto create(ProductRequestDto productReq);
    Page<ProductResponseDto> getAll(int page, int size);
    List<ProductResponseDto> getProductsByCategory(Long categoryId);
    Page<ProductResponseDto> searchProductByName(String keyword, int pageNum, int pageSize);
    ProductResponseDto getProductById(Long id);
}
