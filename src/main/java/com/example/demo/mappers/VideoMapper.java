// package com.example.demo.mappers;

// import org.mapstruct.AfterMapping;
// import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;
// import org.mapstruct.MappingTarget;
// import org.mapstruct.factory.Mappers;

// import com.example.demo.dto.CategoryDto;
// import com.example.demo.dto.VideoDto;
// import com.example.demo.models.Category;
// import com.example.demo.models.Course;
// import com.example.demo.models.User;
// import com.example.demo.models.Video;

// @Mapper
// public interface VideoMapper {

//     VideoMapper INSTANCE = Mappers.getMapper(VideoMapper.class);

//     // @Mapping(source = "course_id", target = "course.id")
//     // @Mapping(target = "id", ignore = true)
//     // @Mapping(target = "create_at", ignore = true)
//     // Video videoDtoToVideo(VideoDto videoDto);

//     // @AfterMapping
//     // default void mapCourse(VideoDto videoDto, @MappingTarget Video video) {
//     //     if (VideoDto.getCourse_id() != null) {
//     //         Course course = new Course();
//     //         course.setId(VideoDto.getCourse_id()); // Set only the ID for the course
//     //         video.setCourse(course);
//     //     }
//     // } 

    
// }
