package com.example.demo.Controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Video.VideoDto;
import com.example.demo.models.User;
import com.example.demo.models.Video;
import com.example.demo.services.VideoService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;
    

   @PostMapping("/create/{courseId}")
   public ResponseEntity<?> createVideo(@RequestBody VideoDto videoDto,
                                        @AuthenticationPrincipal User Authentication,
                                        @PathVariable Integer courseId) {
        
        try {
            Video video = videoService.createVideo(videoDto, Authentication, courseId);      
            return ResponseEntity.ok(video);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getMethodName() {
        List<Video> videos = videoService.getall();
        return ResponseEntity.ok(videos);
    }
}

