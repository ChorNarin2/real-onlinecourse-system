package com.example.demo.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Repository.CategoryRepository;
import com.example.demo.models.Category;
import com.example.demo.services.Categoryservice;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryserviceImpl implements Categoryservice{

    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        Category create = categoryRepository.save(category);
        return create;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


    @Override
    public Category GetById(Integer id) {
        Category category = categoryRepository.getById(id);
        return category;
    }
}


