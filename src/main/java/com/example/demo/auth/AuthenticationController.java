package com.example.demo.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthencationRequset;
import com.example.demo.dto.AuthenticationResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.models.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthencationService authentication;


    @PostMapping("/register")
    public ResponseEntity<RegisterRequest> register( @RequestBody RegisterRequest request){
        return ResponseEntity.ok(authentication.registers(request));
        
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login( @RequestBody AuthencationRequset request){
        return ResponseEntity.ok(authentication.logins(request));
    }


}
