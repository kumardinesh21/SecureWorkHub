package com.example.Rest_API.controller;

import com.example.Rest_API.entity.User;
import com.example.Rest_API.services.MyUserService;
import com.example.Rest_API.services.UserService;
import com.example.Rest_API.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;

@RestController
@RequestMapping("/public")
@Slf4j
public class Public {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private MyUserService myUserService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {

        if (user == null || user.getPassword().isEmpty() || user.getUserName().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userService.create(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
       try{
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
           UserDetails userDetails = myUserService.loadUserByUsername(user.getUserName());
           String jwt = jwtUtil.generateToken(userDetails);
           return new ResponseEntity<>(jwt,HttpStatus.OK);
       }catch (Exception e){
           log.error("Error "+e);
           return new ResponseEntity<>(e,HttpStatus.NOT_FOUND);
       }
    }
}