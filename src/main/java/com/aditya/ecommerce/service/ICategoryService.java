package com.aditya.ecommerce.service;

import com.aditya.ecommerce.dtos.categorydtos.CategoryRequestDto;
import com.aditya.ecommerce.dtos.categorydtos.CategoryResponseDto;

public interface ICategoryService {
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);
}
