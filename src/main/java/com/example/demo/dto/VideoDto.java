package com.example.demo.dto;

import java.util.Locale.Category;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class VideoDto {

    private String file_url;
    private String title;
    private Category category_id; 

}
