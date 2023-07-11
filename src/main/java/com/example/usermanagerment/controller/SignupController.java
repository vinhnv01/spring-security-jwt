package com.example.usermanagerment.controller;

import com.example.usermanagerment.dto.request.CreateUserRequest;
import com.example.usermanagerment.entity.User;
import com.example.usermanagerment.service.LoginService;
import com.example.usermanagerment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/sing-up")
    public ResponseEntity<User> signupUser(@RequestBody CreateUserRequest signupDTO) throws Exception {
        return new ResponseEntity<>(userService.create(signupDTO), HttpStatus.CREATED);
    }

    @GetMapping("/log-out")
    public boolean singOutUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Lấy token từ thông tin xác thực
        boolean token = loginService.token(authentication);
        SecurityContextHolder.clearContext();
        return token;
    }

}
