package com.example.springbootwebs.service;

import com.example.springbootwebs.DTO.PostRequest;
import com.example.springbootwebs.Exception.UserNotFoundException;
import com.example.springbootwebs.model.PostContent;
import com.example.springbootwebs.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

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

    public List<PostContent> getallPosts(){
      String jpql = "SELECT posts FROM PostContent posts";
      TypedQuery<PostContent> typedQuery = entityManager.createQuery(jpql, PostContent.class);
      return typedQuery.getResultList();
    }

    @Transactional
    public String likePost(long postId){
        PostContent existingPostContent = entityManager.find(PostContent.class,postId);
        System.out.println(existingPostContent);

        if (existingPostContent!=null){
            existingPostContent.setLikes(existingPostContent.getLikes()+1);
            entityManager.persist(existingPostContent);
        }
        return "Liked Post SuccessFully ✅";
    }

    @Transactional
    public String dislikePost(long id){
      PostContent postContent = entityManager.find(PostContent.class,id);
      int newLikes = postContent.getLikes()-1;

      if (newLikes<=0){
          postContent.setLikes(0);
      }else{
          postContent.setLikes(newLikes);
      }
      entityManager.persist(postContent);
      return "Like Removed SuccessFully ✅";
    }
}
