package com.example.springbootwebs.controller;

import com.example.springbootwebs.model.PostContent;
import com.example.springbootwebs.service.SearchPostsContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SearchPostsContent {
    @Autowired
    SearchPostsContentService searchPostsContentService;
    @GetMapping("/search/posts")
    public List<PostContent> searchPostsContent(@RequestParam("query") String query){
        List<PostContent> postContentList = searchPostsContentService.searchPostsContent(query);
        return postContentList;
    }

}
