package com.example.demo.controller;

import com.example.demo.model.Users;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public Users createUser(@RequestBody Users users) {
        return userService.saveUser(users);
    }



    @GetMapping("email/{email}")
    public Users getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        Users user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
