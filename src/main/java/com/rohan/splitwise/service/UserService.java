package com.rohan.splitwise.service;

import com.rohan.splitwise.models.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserService {
    Map<String, User> userMap;

    public UserService() {
        this.userMap = new HashMap<>();
    }

    public User getUser(String userId) {
        return userMap.get(userId);
    }

    public void putUser(String userId, User user) {
        userMap.put(userId, user);
    }
}
