package com.rohan.splitwise.controller;

import com.rohan.splitwise.models.User;
import com.rohan.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class usercontroller {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{userId}")
    public User getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }

    @PostMapping("/create-user")
    public User createUser(@RequestBody User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        userService.putUser(userId, user);
        return user;
    }

}
