package com.example.usermanagerment.controller;


import com.example.usermanagerment.dto.auth.LoginRequest;
import com.example.usermanagerment.dto.auth.LoginResponse;
import com.example.usermanagerment.service.LoginService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

    @Resource
    private LoginService loginService;

    @PostMapping("/authenticate")
    public LoginResponse createAuthenticationToken(@RequestBody LoginRequest request, HttpServletRequest httpServletRequest)
            throws Exception {

        return loginService.login(httpServletRequest, request);

    }

}
