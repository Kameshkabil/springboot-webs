package com.example.springbootwebs.service;

import com.example.springbootwebs.model.Comment;
import com.example.springbootwebs.model.PostContent;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RemoveCommentService {
    @Autowired
    EntityManager entityManager;

    @Transactional
    public String deleteComment(long commentId){
        Comment comment =  entityManager.find(Comment.class,commentId);
        if (comment!=null){
           PostContent postContent = comment.getPostContent();
           if(postContent!=null){
                postContent.getComments().remove(comment);
                entityManager.merge(postContent);
                entityManager.remove(comment);
           }
        }
        return "Comment Deleted Successfully!✅";
    }
}
