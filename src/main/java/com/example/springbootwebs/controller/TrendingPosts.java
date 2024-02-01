package com.example.springbootwebs.controller;

import com.example.springbootwebs.model.PostContent;
import com.example.springbootwebs.service.TrendingPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TrendingPosts {
    @Autowired
    TrendingPostsService trendingPostsService;
    @GetMapping("/trends")
    public List<PostContent> getTrendingPost(){
        return trendingPostsService.getTredingPost();
    }
}
