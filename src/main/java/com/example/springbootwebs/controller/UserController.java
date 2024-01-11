package com.example.springbootwebs.controller;

import com.example.springbootwebs.model.User;
import com.example.springbootwebs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/create-user")
    public String createNewAccount(@RequestBody User user){
        return userService.createNewAccount(user);
    }
}
