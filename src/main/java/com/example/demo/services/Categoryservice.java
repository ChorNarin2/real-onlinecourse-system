package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.Category.CategoryDto;
import com.example.demo.models.Category;

public interface Categoryservice {
    Category createCategory(CategoryDto categoryDto);
    List<Category> getAllCategories();
    Category GetById(Integer id);
    


}
