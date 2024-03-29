package com.example.springbootwebs.service;

import com.example.springbootwebs.Exception.UserNotFoundException;
import com.example.springbootwebs.model.PostContent;
import com.example.springbootwebs.model.User;
import com.example.springbootwebs.repo.UserRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<User> getAllUsers(){
        String jpql = "SELECT u FROM User u";
        TypedQuery<User> typedQuery = entityManager.createQuery(jpql,User.class);
        return typedQuery.getResultList();
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


    public Map<String,Long> findUserPostCount(long userId){
        String jpql = "SELECT COUNT(p) FROM PostContent p WHERE p.user.id = :userId";
        TypedQuery<Long> typedQuery = entityManager.createQuery(jpql,Long.class);
        typedQuery.setParameter("userId", userId);
        Long postCount = typedQuery.getSingleResult();

        Map<String,Long> postCountResult = new HashMap<>();
        postCountResult.put("No.Of Post Counts",postCount);

        return postCountResult;
    }
}
