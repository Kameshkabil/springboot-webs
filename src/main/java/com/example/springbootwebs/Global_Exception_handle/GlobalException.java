package com.example.springbootwebs.Global_Exception_handle;

import com.example.springbootwebs.Exception.UserNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(UserNotFoundException.class)
    public Map<String,String> userNotFoundException(UserNotFoundException e){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("message",e.getMessage());
        return errorMap;
    }
}
