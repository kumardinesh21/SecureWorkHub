package com.example.Rest_API.services;

import com.example.Rest_API.entity.User;
import com.example.Rest_API.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUserName = userRepo.findByUserName(username);
        if (byUserName != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(byUserName.getUserName())
                    .password(byUserName.getPassword())
                    .roles(byUserName.getRole().toArray(new String[0]))
                    .build();
        }
        throw new UsernameNotFoundException("User not found" + username);
    }
}
