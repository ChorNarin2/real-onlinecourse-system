package com.example.demo.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.Category.CategoryDto;
import com.example.demo.models.Category;
import com.example.demo.models.User;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "create_at", ignore = true)
    @Mapping(target = "user", ignore = true) // Ignore user initially
    Category toCategory(CategoryDto categoryDto);

    @AfterMapping
    default void mapUser(CategoryDto categoryDto, @MappingTarget Category category) {
        if (categoryDto.getUser_id() != null) {
            User user = new User();
            user.setId(categoryDto.getUser_id()); // Set only the ID for the user
            category.setUser(user);
        }
    }

}
