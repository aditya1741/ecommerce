package com.aditya.ecommerce.controller;

import com.aditya.ecommerce.dtos.productdtos.ProductRequestDto;
import com.aditya.ecommerce.dtos.productdtos.ProductResponseDto;
import com.aditya.ecommerce.service.IService.IProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductResponseDto createProduct(@Valid @RequestBody ProductRequestDto prodReq) {
        return productService.create(prodReq);
    }

    @GetMapping
    public Page<ProductResponseDto> getAllProduct(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return productService.getAll(page, size);
    }

    @GetMapping("category/{id}")
    public List<ProductResponseDto> getProductByCategory(@PathVariable("id") Long categoryId) {
        return productService.getProductsByCategory(categoryId);
    }

    @GetMapping("/search")
    public Page<ProductResponseDto> searchProductByName(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return productService.searchProductByName(keyword, page, size);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

}
