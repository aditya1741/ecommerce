package com.aditya.ecommerce.controller;

import com.aditya.ecommerce.dtos.productdtos.ProductRequestDto;
import com.aditya.ecommerce.dtos.productdtos.ProductResponseDto;
import com.aditya.ecommerce.entity.Product;
import com.aditya.ecommerce.service.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final IProductService productService;
    public ProductController(IProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public ProductResponseDto createUser(@RequestBody ProductRequestDto prodReq){
        return  productService.create(prodReq);
    }
    @GetMapping
    public Page<ProductResponseDto> getAllProduct(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return productService.getAll(page, size);
    }
    @GetMapping("category/{id}")
    public List<ProductResponseDto>  getProductByCategory(@PathVariable("id") Long categoryId){
        return productService.getProductsByCategory(categoryId);
    }
    @GetMapping("/search")
    public Page<ProductResponseDto> searchProductByName(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return productService.searchProductByName(keyword, page, size);
    }

}
