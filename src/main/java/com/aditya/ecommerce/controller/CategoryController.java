package com.aditya.ecommerce.controller;

import com.aditya.ecommerce.dtos.categorydtos.CategoryRequestDto;
import com.aditya.ecommerce.dtos.categorydtos.CategoryResponseDto;
import com.aditya.ecommerce.service.ICategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto catReq){
        return categoryService.createCategory(catReq);
    }


}
