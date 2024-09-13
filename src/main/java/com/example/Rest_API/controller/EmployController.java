package com.example.Rest_API.controller;

import com.example.Rest_API.entity.Employee;
import com.example.Rest_API.entity.User;
import com.example.Rest_API.services.EmployService;
import com.example.Rest_API.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emp")
public class EmployController {
    @Autowired
    private EmployService employService;
    // now connect
    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody Employee employee) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            employService.create(employee, userName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllUserEntry() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User byUserName = userService.findByUserName(userName);
        List<Employee> obj = byUserName.getEmployeeList();
        if (obj != null && !obj.isEmpty()) {
            return new ResponseEntity<>(obj, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getbyid(@PathVariable ObjectId id) {
        Optional<Employee> obj = employService.getid(id);
        if (obj.isPresent()) {
            return new ResponseEntity<>(obj, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> del(@PathVariable ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Optional<Employee> obj = employService.getid(id);
        if (obj.isPresent()) {
            employService.del(id, userName);
            return new ResponseEntity<>("\"Employee deleted successfully\",", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("id/{id}")
    public ResponseEntity<?> update(@RequestBody Employee add, @PathVariable ObjectId id) {
        Optional<Employee> obj = employService.getid(id);
        if (obj.isPresent()) {
            employService.edit(add, id);
            return new ResponseEntity<>("\"Employee update successfully\",", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
