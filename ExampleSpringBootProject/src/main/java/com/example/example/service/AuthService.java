package com.example.example.service;

import com.example.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {

    @Autowired
    private UserService userService;


    public User login(String username, String password) {
        User user = this.userService.findByUsername(username);
        System.out.println(user);
        if(user == null) {
            return null;
        }
        if (Objects.equals(user.getPassword(), password)) {
            return user;
        }
        return null;
    }
}
