package com.practice.splitwise.controller;


import com.practice.splitwise.dto.CreateUserDTO;
import com.practice.splitwise.dto.ResponseDTO;
import com.practice.splitwise.dto.UserDTO;
import com.practice.splitwise.model.User;
import com.practice.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public UserDTO createUser(@RequestBody CreateUserDTO createUserRequest){
        return userService.createUser(createUserRequest);
    }

    @GetMapping("/user/{userId}")
    public ResponseDTO<UserDTO> getUser(@PathVariable Long userId){
        Optional<User> user = userService.getUser(userId);
        Optional<UserDTO> userDTO = user.map(UserDTO::from);

        // return user.map(user1 -> UserDTO.from(user1)).orElse(null);

        // In english interpretation - If UserDTO value present, map it to SUCCESS response, otherwise map to NOT_FOUND
        return userDTO.map(ResponseDTO::success).orElse(ResponseDTO.notFound());
        // lambda equivalent: userDTO.map(userObject -> ResponseDTO.success(userObject)).orElse(ResponseDTO.notFound());
    }

    @PutMapping("/user/{userId}")
    public ResponseDTO<UserDTO> updateUser(@PathVariable Long userId, @RequestBody CreateUserDTO createUserDTO){
        UserDTO userDTO =  userService.updateUser(userId, createUserDTO);
        if(userDTO == null){
            return ResponseDTO.notFound();
        }
        return ResponseDTO.success(userDTO);
    }
}

// Create user = HTTP VERB => POST ENDPOINT => /api/v1/user
// Get user = HTTP VERB => GET ENDPOINT => /api/v1/user/:userID
// Each HTTP response has status code
//  - request successful - 200
//  - Entity not found - 404
//  - Invalid Request/input - 400
// In General
//  - 2XX - OK
//  - 4XX - User Error
//  - 5XX - Server Error
