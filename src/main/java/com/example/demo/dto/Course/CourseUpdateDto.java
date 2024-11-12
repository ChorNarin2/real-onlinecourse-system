package com.example.demo.dto.Course;

import java.math.BigDecimal;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CourseUpdateDto {

    private String description;
    private String title;
    private BigDecimal price;


}
