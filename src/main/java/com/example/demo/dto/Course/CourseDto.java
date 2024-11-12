package com.example.demo.dto.Course;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CourseDto {



    private String title;
    private String description;
    private BigDecimal price;
    private Integer category_id;

    public Integer getCategory_id() {
        return category_id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }
}
