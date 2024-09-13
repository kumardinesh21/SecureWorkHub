package com.example.Rest_API.services;


import com.example.Rest_API.entity.User;
import com.example.Rest_API.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {

    @Autowired
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserRepo userrepo;

    public boolean create(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(Arrays.asList("USER","ADMIN"));
            userrepo.save(user);
            return true;
        }catch (Exception e){
            log.error("HAHA", e);
            log.info("HAHA");
            log.warn("HAHA");
            log.debug("HAHA");

            return false;

        }



    }

    public void createemp(User user) {
        userrepo.save(user);
    }


    public List<User> getall() {
        return userrepo.findAll();

    }

    public Optional<User> getid(ObjectId id) {

        return userrepo.findById(id);

    }

    public User findByUserName(String userName) {
        return userrepo.findByUserName(userName);

    }

    public void deleteByUserName(String userName) {
        userrepo.deleteByUserName(userName);

    }




}