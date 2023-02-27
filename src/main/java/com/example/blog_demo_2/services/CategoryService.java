package com.example.blog_demo_2.services;


import com.example.blog_demo_2.payload.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

    void deleteCategory(Integer categoryId);

    List<CategoryDto> getAllCategory();

    CategoryDto getCategoryById(Integer categoryId);
}
