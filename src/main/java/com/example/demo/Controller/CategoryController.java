package com.example.demo.Controller;

import org.hibernate.mapping.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Category;
import com.example.demo.services.Categoryservice;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
@PreAuthorize("hasRole('ADMIN')")
public class CategoryController {

    private final Categoryservice categoryservice;

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody Category categoryDto){
        return ResponseEntity.ok(categoryservice.createCategory(categoryDto));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCategories(){
        return ResponseEntity.ok(categoryservice.getAllCategories());
    }

    @GetMapping("/getbyId")
    public ResponseEntity<?> getbyId(Integer id){
        return ResponseEntity.ok(categoryservice.GetById(id));
    }

}
