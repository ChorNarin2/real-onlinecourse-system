package com.example.demo.Impl;


import org.springframework.stereotype.Service;

import java.util.List;
import com.example.demo.Repository.CourseRepository;
import com.example.demo.Repository.VideoRepository;
import com.example.demo.dto.Video.VideoDto;
import com.example.demo.models.Course;
import com.example.demo.models.User;
import com.example.demo.models.Video;
import com.example.demo.models.Enum.VideoEnumStatus;
import com.example.demo.services.VideoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VideoImpl implements VideoService {
    private final VideoRepository videoRepository;
    private final CourseRepository courseRepository;


    @Override
    public Video createVideo(VideoDto videoDto, User authenticatedUser, Integer courseId) {

        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

        // Check if the authenticated user is the course owner
        if (!course.getUser().getId().equals(authenticatedUser.getId())) {
            throw new RuntimeException("You are not owner of  this course");
        }
    
        // Create and save the video as usual
        Video video = new Video();
        video.setTitle(videoDto.getTitle());
        video.setDescription(videoDto.getDescription());
        video.setFile_url(videoDto.getFile_url());
        video.setUser(authenticatedUser);
        video.setStatus(VideoEnumStatus.PROGRESS);
        video.setCourse(course);
    
        return videoRepository.save(video);
    }

@Override
public List<Video> getall() {
    List<Video> getallvideo = videoRepository.findAll();
    return getallvideo;
}


@Override
public List<Video> findByCourseId(Integer courseId) {
    throw new UnsupportedOperationException("Unimplemented method 'findByCourseId'");
}
@Override
public Video confirmVideoActivation(Integer videoId, User authenticatorAdmin) {
    Video video = videoRepository.findById(videoId)
           .orElseThrow(() -> new RuntimeException("Video not found with id: " + videoId));

    // Check if the authenticated user is the admin
    if (!video.getUser().getId().equals(authenticatorAdmin.getId())) {
        throw new RuntimeException("You are not authorized to confirm this video");
    }

    // Change the status of the video to ACTIVE
    video.setStatus(VideoEnumStatus.ACTIVE);
    return videoRepository.save(video);
}
}
