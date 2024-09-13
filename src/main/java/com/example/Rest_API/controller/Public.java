package com.example.Rest_API.controller;

import com.example.Rest_API.entity.User;
import com.example.Rest_API.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/public")
public class Public {

    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user) {

        if (user == null || user.getPassword().isEmpty() || user.getUserName().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userService.create(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
