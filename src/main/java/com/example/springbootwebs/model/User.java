package com.example.springbootwebs.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = "postContentList")
@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String mobile;
    private String location;

    @OneToMany(mappedBy = "user" /*, cascade = CascadeType.ALL, fetch = FetchType.EAGER*/)
    private List<PostContent> postContentList = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<PostContent> getPostContentList() {
        return postContentList;
    }

    public void setPostContentList(List<PostContent> postContentList) {
        this.postContentList = postContentList;
    }
}
