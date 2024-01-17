package com.example.springbootwebs.controller;

import com.example.springbootwebs.service.RemoveCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RemoveCommentController {
    @Autowired
    RemoveCommentService removeCommentService;
    @DeleteMapping("/delete-comment/{id}")
    public String deleteComment(@PathVariable(value = "id") long id){
        return removeCommentService.deleteComment(id);
    }
}
