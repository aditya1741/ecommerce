package com.aditya.ecommerce.service;

import com.aditya.ecommerce.dtos.productdtos.ProductRequestDto;
import com.aditya.ecommerce.dtos.productdtos.ProductResponseDto;
import com.aditya.ecommerce.entity.Category;
import com.aditya.ecommerce.entity.Product;
import com.aditya.ecommerce.repository.CategoryRepository;
import com.aditya.ecommerce.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        return ProductResponseDto.builder()
                .Id(savedProduct.getId())
                .name(savedProduct.getName())
                .price(savedProduct.getPrice())
                .categoryName(savedProduct.getCategory().getName())

                .build();
    }

    @Override
    public Page<ProductResponseDto> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageRequest);

        return productPage.map(product -> ProductResponseDto
                .builder()
                .Id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .categoryName(product.getCategory().getName()).build());

    }

    @Override
    public ProductResponseDto getById(Long id) {

        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found with Id " + id));

        return ProductResponseDto.builder()
                .Id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .categoryName(product.getCategory().getName())
                .build();
    }

    @Override
    public List<ProductResponseDto> getProductsByCategory(Long categoryId) {
        List<Product> productList = productRepository.findByCategoryId(categoryId);
        List<ProductResponseDto> dtoList = new ArrayList<>();
        for (Product currProduct : productList) {
            dtoList.add(
                    ProductResponseDto.builder()
                            .Id(currProduct.getId())
                            .name(currProduct.getName())
                            .description(currProduct.getDescription())
                            .categoryName(currProduct.getCategory().getName())
                            .price(currProduct.getPrice())
                            .build()
            );
        }
        return dtoList;
    }

    @Override
    public Page<ProductResponseDto> searchProductByName(String keyword, int pageNum, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        Page<Product> productPage = productRepository.findByNameContainingIgnoreCase(keyword, pageRequest);
        return productPage.map(product -> ProductResponseDto
                .builder()
                .Id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .categoryName(product.getCategory().getName())
                .build());

    }


}
