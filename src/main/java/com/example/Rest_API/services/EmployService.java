package com.example.Rest_API.services;


import com.example.Rest_API.entity.Employee;
import com.example.Rest_API.entity.User;
import com.example.Rest_API.repo.EmployRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class EmployService {
    @Autowired
    private EmployRepo employRepo;
    @Autowired
    private UserService userService;

@Transactional
    public void create(Employee emp, String userName) {
        try {
            User byUserName = userService.findByUserName(userName);
            Employee save = employRepo.save(emp);
            byUserName.getEmployeeList().add(save);
            userService.createemp(byUserName);
        } catch (Exception e) {
            throw new RuntimeException("Error :" + e);
        }
    }

    public List<Employee> getall() {
        return employRepo.findAll();

    }

    public Optional<Employee> getid(ObjectId id) {

        return employRepo.findById(id);

    }

    public void del(ObjectId id, String userName) {
        User byUserName = userService.findByUserName(userName);
        if (byUserName != null) {
            byUserName.getEmployeeList().removeIf(x -> x.getId().equals(id));
            employRepo.deleteById(id);
            userService.createemp(byUserName);
        }
    }
    public void edit(Employee add, ObjectId id) {

        Optional<Employee> obj = employRepo.findById(id);
        if (obj.isPresent()) {
            Employee employee = obj.get();
            employee.setName(add.getName());
            employee.setJob(add.getJob());
            employRepo.save(employee);
        }


    }


}


