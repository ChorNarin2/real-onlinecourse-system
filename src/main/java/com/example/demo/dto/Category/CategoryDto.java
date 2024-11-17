package com.example.demo.dto.Category;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CategoryDto {

    private String name;
    private Integer user_id;

}
