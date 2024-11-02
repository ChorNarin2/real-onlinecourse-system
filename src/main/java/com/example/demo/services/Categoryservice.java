package com.example.demo.services;

import java.util.List;
import com.example.demo.models.Category;

public interface Categoryservice {
    Category createCategory(Category category);
    List<Category> getAllCategories();
    Category GetById(Integer id);

}
