package com.example.Rest_API.controller;

import com.example.Rest_API.entity.User;
import com.example.Rest_API.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class Admin {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<?> getalluser(){
        List<User> getall = userService.getall();
        if (getall!=null && !getall.isEmpty()){
            return new ResponseEntity<>(getall, HttpStatus.OK);

        }
        return new ResponseEntity<>(getall, HttpStatus.FOUND);

    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user) {
        if (user == null || user.getPassword().isEmpty() || user.getUserName().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.create(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
