package com.example.springbootwebs.service;

import com.example.springbootwebs.DTO.CommentRequest;
import com.example.springbootwebs.model.Comment;
import com.example.springbootwebs.model.PostContent;
import com.example.springbootwebs.model.User;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateCommentService {
    @Autowired
    EntityManager entityManager;

    @Transactional
    public String createCommentForExistingPost(CommentRequest commentRequest){
        PostContent postContent = entityManager.find(PostContent.class,commentRequest.getPostId());
        User user = entityManager.find(User.class,commentRequest.getUserId());
        if (postContent!=null && user!=null){
            Comment comment = new Comment();
            comment.setComment(commentRequest.getComment());
            comment.setPostContent(postContent);
            comment.setUserId(commentRequest.getUserId());
            entityManager.persist(comment);
        }
        return "Comment Added Successfully!✅";
    }
}
