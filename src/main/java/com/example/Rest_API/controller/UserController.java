package com.example.Rest_API.controller;


import com.example.Rest_API.entity.Employee;
import com.example.Rest_API.entity.User;
import com.example.Rest_API.repo.UserRepo;
import com.example.Rest_API.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<?> getbyuser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User obj = userService.findByUserName(username);
        if (obj == null ) {
            return new ResponseEntity<>("Not found invalid", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(obj, HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User byUserName = userService.findByUserName(name);
        byUserName.setPassword(user.getPassword());
        byUserName.setUserName(user.getUserName());
        userService.create(byUserName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
