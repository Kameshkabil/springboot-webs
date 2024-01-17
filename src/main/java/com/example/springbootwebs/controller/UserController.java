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

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable(value = "id") long id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/user/{id}")
    public String deleteUserProfile(@PathVariable(value = "id") long id){
        return userService.deleteUserProfile(id);
    }
}
