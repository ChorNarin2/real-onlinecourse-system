package com.example.demo.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.Course.CourseDto;
import com.example.demo.models.Category;
import com.example.demo.models.Course;
import com.example.demo.models.User;

@Mapper
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created_at", ignore = true)
    @Mapping(target = "category", ignore = true)
    // @Mapping(target = "user", ignore = true)
    @Mapping(target = "video", ignore = true)
    Course toCourse(CourseDto courseDto, User user);

    @AfterMapping
    default void mapCategory(CourseDto courseDto, @MappingTarget Course course) {
        if (courseDto.getCategory_id() != null) {
            Category category = new Category();
            category.setId(courseDto.getCategory_id()); // Set only the ID for the user
            course.setCategory(category);
        }
    };

}
