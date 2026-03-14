package com.example.bank_statement_aggregator.controllers;

import com.example.bank_statement_aggregator.dto.UserLoginRequest;
import com.example.bank_statement_aggregator.dto.UserRegisterRequest;
import com.example.bank_statement_aggregator.models.User;
import com.example.bank_statement_aggregator.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody UserRegisterRequest request) {
        return userService.registerUser(request);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody UserLoginRequest request) {
        return userService.loginUser(request);
    }
}