package com.example.springbootwebs.controller;

import com.example.springbootwebs.DTO.CommentRequest;
import com.example.springbootwebs.service.CreateCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CreateCommentController {
    @Autowired
    CreateCommentService createCommentService;
    @PostMapping("/create-comment")
    public String createCommentForExistingPost(@RequestBody CommentRequest commentRequest){
        return createCommentService.createCommentForExistingPost(commentRequest);
    }

}
