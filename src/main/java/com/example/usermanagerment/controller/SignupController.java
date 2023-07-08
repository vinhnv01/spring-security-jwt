package com.example.usermanagerment.controller;

import com.example.usermanagerment.dto.request.CreateUserRequest;
import com.example.usermanagerment.entity.User;
import com.example.usermanagerment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<User> signupUser(@RequestBody CreateUserRequest signupDTO) {
       return new ResponseEntity<>(userService.create(signupDTO), HttpStatus.CREATED);
    }



}
