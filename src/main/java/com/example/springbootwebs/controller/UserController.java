package com.example.springbootwebs.controller;

import com.example.springbootwebs.excel.UserExecelData;
import com.example.springbootwebs.model.PostContent;
import com.example.springbootwebs.model.User;
import com.example.springbootwebs.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/create-user")
    public String createNewAccount(@RequestBody User user){
        return userService.createNewAccount(user);
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable(value = "id") long id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/user/{id}")
    public String deleteUserProfile(@PathVariable(value = "id") long id){
        return userService.deleteUserProfile(id);
    }

    @PutMapping("/user")
    public User upadateUserprofile(@RequestBody User user,@PathVariable(value = "id")
                                   long id){
        return userService.updateUserProfiles(user,id);
    }

    @GetMapping("/view-all-users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/export")
    public String exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users.xlsx";

        response.setHeader(headerKey, headerValue);

        List<User> userList = userService.getAllUsers();

        UserExecelData userExcelData = new UserExecelData(userList);
        userExcelData.export(response);
        return "Downloaded Excel File ✅";
    }

    @GetMapping("/find-my-post-count/{id}")
    public Map<String,Long> findMyPostCount(@PathVariable(value = "id") long id){
        return userService.findUserPostCount(id);
    }
}
