package com.example.demo.Impl;

import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.UserNotFoundException;
import com.example.demo.Repository.UserRepository;

import lombok.RequiredArgsConstructor;
import com.example.demo.models.User;



@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;

   public User findUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));
    }

}
