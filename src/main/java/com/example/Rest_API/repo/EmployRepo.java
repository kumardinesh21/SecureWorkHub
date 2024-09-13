package com.example.Rest_API.repo;

import com.example.Rest_API.entity.Employee;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployRepo extends MongoRepository<Employee, ObjectId> {

}
