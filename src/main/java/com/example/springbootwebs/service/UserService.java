package com.example.springbootwebs.service;

import com.example.springbootwebs.Exception.UserNotFoundException;
import com.example.springbootwebs.model.User;
import com.example.springbootwebs.repo.UserRepo;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    EntityManager entityManager;
    @Autowired
    UserRepo userRepo;

    private final Logger LOG = LoggerFactory.getLogger(SpringApplication.class);
    public String createNewAccount(User user){
     user.setName(user.getName());
     user.setMobile(user.getMobile());
     user.setLocation(user.getLocation());
     user.setEmail(user.getEmail());
     this.userRepo.save(user);
     return "User Account Created Successfully";
    }


    @Cacheable(cacheNames = "users",key = "#id")
    public User getUserById(long id){
        LOG.info("cache First time only fetched data to db");
        User user  = entityManager.find(User.class , id);
        if (user!=null){
            return user;
        }else {
            throw new UserNotFoundException("USER :"+id+" not found");
        }

    }


    @Transactional
    @CacheEvict(cacheNames = "users",key = "#id")
    public String deleteUserProfile(long id){
        User user = entityManager.find(User.class,id);
        if (user!=null){
            entityManager.remove(user);
            return "USER :"+id+" removed successfully✅";
        }else{
            throw new UserNotFoundException("USER :"+id+" not found");
        }
    }


    @Transactional
    @CachePut(cacheNames = "users",key = "#user.id")
    public User updateUserProfiles(User user,long id){
        User existingUser = entityManager.find(User.class,id);
        if(existingUser!=null){
            entityManager.merge(user);
            return user;
        }else {
            throw new UserNotFoundException("USER :"+id+" not found");
        }
    }

}
