package com.example.demo.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.User;

@RestController
@RequestMapping("/test")
public class demoController {

    @GetMapping("/controller")
    public User home(@AuthenticationPrincipal User user ) {
        return user;
    }

}
