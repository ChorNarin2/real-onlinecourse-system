package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Category;



@Repository
public interface CategoryRepository  extends JpaRepository<Category, Integer> {
    Optional<Category> findByName(String name);
    
}
