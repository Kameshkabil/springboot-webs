package com.example.springbootwebs.service;

import com.example.springbootwebs.DTO.PostRequest;
import com.example.springbootwebs.Exception.UserNotFoundException;
import com.example.springbootwebs.model.PostContent;
import com.example.springbootwebs.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Service
public class PostService {
    @Autowired
    EntityManager entityManager;

  @Transactional
    public PostContent createPostContent(PostRequest postRequest) throws UserNotFoundException{
        User user = entityManager.find(User.class,postRequest.getUserId());
        if (user!=null){
            PostContent postContent = new PostContent();
            postContent.setTitle(postRequest.getTitle());
            postContent.setContent(postRequest.getContent());
            postContent.setUser(user);
            entityManager.persist(postContent);
            //entityManager.refresh(postContent);
            return postContent;
        }else{
            throw new UserNotFoundException("This "+postRequest.getUserId()+"not fount. please first create your own account then post the content. ");
        }
    }

}
