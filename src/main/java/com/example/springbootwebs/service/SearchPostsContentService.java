package com.example.springbootwebs.service;

import com.example.springbootwebs.Exception.ContentNotFoundException;
import com.example.springbootwebs.model.PostContent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchPostsContentService {
    @Autowired
    EntityManager entityManager;

    public List<PostContent> searchPostsContent(String query){
        String jpql = "SELECT posts FROM PostContent posts WHERE posts.title LIKE :searchTerm";
        TypedQuery<PostContent> typedQuery = entityManager.createQuery(jpql, PostContent.class);
        typedQuery.setParameter("searchTerm",query+"%");

        List<PostContent> resultList =typedQuery.getResultList();

        if (resultList.isEmpty()){
            throw new ContentNotFoundException(query+" content not found");
        }
        return resultList;
    }
}
