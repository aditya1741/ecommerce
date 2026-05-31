package com.aditya.ecommerce.service.impl;

import com.aditya.ecommerce.dtos.productdtos.ProductRequestDto;
import com.aditya.ecommerce.dtos.productdtos.ProductResponseDto;
import com.aditya.ecommerce.entity.Category;
import com.aditya.ecommerce.entity.Product;
import com.aditya.ecommerce.exception.ProductNotFoundException;
import com.aditya.ecommerce.mapper.ProductMapper;
import com.aditya.ecommerce.repository.CategoryRepository;
import com.aditya.ecommerce.repository.ProductRepository;
import com.aditya.ecommerce.service.IService.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductResponseDto create(ProductRequestDto productReq) {
        Category category = categoryRepository.findById(productReq.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category Not Found"));

        Product product = Product.builder()
                .name(productReq.getName())
                .price(productReq.getPrice())
                .description(productReq.getDescription())
                .stock(productReq.getStock())
                .category(category)
                .createdAt(LocalDateTime.now())
                .build();
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toResponseDto(savedProduct);
    }

    @Override

    public Page<ProductResponseDto> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageRequest);

        return productPage.map(ProductMapper::toResponseDto);

    }


    @Override
    public List<ProductResponseDto> getProductsByCategory(Long categoryId) {
        List<Product> productList = productRepository.findByCategoryId(categoryId);
        List<ProductResponseDto> dtoList = new ArrayList<>();
        for (Product currProduct : productList) {
            dtoList.add(ProductMapper.toResponseDto(currProduct));
        }
        return dtoList;
    }

    @Override
    public Page<ProductResponseDto> searchProductByName(String keyword, int pageNum, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        Page<Product> productPage = productRepository.findByNameContainingIgnoreCase(keyword, pageRequest);
        return productPage.map(ProductMapper::toResponseDto);

    }

    @Override

    public ProductResponseDto getProductById(Long id) {
       Product resultProduct =  productRepository.findById(id)
               .orElseThrow(() -> new ProductNotFoundException("Product Not Found with Id " + id));

        return ProductMapper.toResponseDto(resultProduct);
    }


}
