package com.example.springbootwebs.service;

import com.example.springbootwebs.model.PostContent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrendingPostsService {
    @Autowired
    EntityManager entityManager;

    public List<PostContent> getTredingPost(){
        String jpql = "SELECT p FROM PostContent p ORDER BY p.likes DESC LIMIT 1";
        TypedQuery<PostContent> typedQuery = entityManager.createQuery(jpql, PostContent.class);
        return typedQuery.getResultList();
    }
}
