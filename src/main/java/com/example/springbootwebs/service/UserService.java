package com.example.springbootwebs.service;

import com.example.springbootwebs.model.User;
import com.example.springbootwebs.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    public String createNewAccount(User user){
     user.setName(user.getName());
     user.setMobile(user.getMobile());
     user.setLocation(user.getLocation());
     user.setEmail(user.getEmail());
     this.userRepo.save(user);
     return "User Account Created Successfully";
    }
}
