package com.example.springbootwebs.controller;

import com.example.springbootwebs.DTO.PostRequest;
import com.example.springbootwebs.Exception.UserNotFoundException;
import com.example.springbootwebs.model.PostContent;
import com.example.springbootwebs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    PostService postService;
    @PostMapping("/create-post")
    public ResponseEntity<PostContent> createPostContent(@RequestBody PostRequest postRequest) {
        PostContent postContent = this.postService.createPostContent(postRequest);
        return new ResponseEntity<>(postContent, HttpStatus.CREATED);
    }

    @GetMapping("all/posts")
    public List<PostContent> getallPostsContent(){
        return postService.getallPosts();
    }

}
