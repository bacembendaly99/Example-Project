package com.example.example.controller;

import com.example.example.model.User;
import com.example.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> findAll() {
        return this.userService.findAll();
    }

    @PostMapping("")
    public User save(@RequestBody User user) {
        return this.userService.save(user);
    }

    @PutMapping("")
    public User update(@RequestBody User user) {
        return this.userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        this.userService.delete(id);
    }

}
