package com.aditya.ecommerce.service.impl;

import com.aditya.ecommerce.dtos.categorydtos.CategoryRequestDto;
import com.aditya.ecommerce.dtos.categorydtos.CategoryResponseDto;
import com.aditya.ecommerce.entity.Category;
import com.aditya.ecommerce.entity.Product;
import com.aditya.ecommerce.repository.CategoryRepository;
import com.aditya.ecommerce.service.IService.ICategoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class CategoryServiceImpl implements ICategoryService {
    private final CategoryRepository categoryRepo;

    public CategoryServiceImpl(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
        Category category = Category.builder()
                .name(categoryRequestDto.getName())
                .createdAt(LocalDateTime.now())
                .products(new ArrayList<Product>())
                .build();
        Category savedCategory = categoryRepo.save(category);
        return CategoryResponseDto.builder()
                .Id(savedCategory.getId())
                .name(savedCategory.getName())
                .build();

    }
}
