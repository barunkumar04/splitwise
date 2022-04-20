package com.practice.splitwise.controller;


import com.practice.splitwise.dto.CreateUserDTO;
import com.practice.splitwise.dto.UserDTO;
import com.practice.splitwise.model.User;
import com.practice.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
//@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public UserDTO createUser(@RequestBody CreateUserDTO createUserRequest){
        return userService.createUser(createUserRequest);
    }

    @GetMapping("/user/{userId}")
    public UserDTO getUser(@PathVariable Long userId){
        Optional<User> user = userService.getUser(userId);
        return user.map(UserDTO::from).orElse(null); // or, user.map(user -> UserDTO.from(user)).orElse(null)
    }
}

// Create user = HTTP VERB => POST ENDPOINT => /api/v1/user
// Get user = HTTP VERB => GET ENDPOINT => /api/v1/user/:userID
