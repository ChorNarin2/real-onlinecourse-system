package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.Video.VideoDto;
import com.example.demo.models.User;
import com.example.demo.models.Video;

public interface VideoService {

    // Video createVideo(VideoDto VideoDto);
    Video createVideo(VideoDto videoDto, User authenticatedUser, Integer courseId);
    List<Video> getall();
    List<Video> findByCourseId(Integer courseId);

    Video confirmVideoActivation(Integer videoId, User authenticatorAdmin);
}
