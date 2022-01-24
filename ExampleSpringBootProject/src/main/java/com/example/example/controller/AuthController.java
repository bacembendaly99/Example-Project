package com.example.example.controller;

import com.example.example.LoginDTO;
import com.example.example.model.User;
import com.example.example.service.AuthService;
import com.example.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public User login(@RequestBody LoginDTO loginDTO) {
        User user = this.authService.login(loginDTO.getUsername(), loginDTO.getPassword());
        if(user == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Login Error"
            );
        }
        return user;
    }


}

