package com.example.Rest_API.repo;

import com.example.Rest_API.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo  extends MongoRepository<User,ObjectId> {
    User findByUserName(String username);


    User deleteByUserName(String username);
}
