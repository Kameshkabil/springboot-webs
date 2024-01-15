package com.example.springbootwebs.Exception;

public class ContentNotFoundException extends RuntimeException {
    public ContentNotFoundException(String message){
        super(message);
    }
}
