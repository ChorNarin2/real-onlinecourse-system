package com.example.demo.dto.Video;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class VideoDto {

    private String file_url;
    private String title;
    private String description;
    // private Integer course_id; 

}
