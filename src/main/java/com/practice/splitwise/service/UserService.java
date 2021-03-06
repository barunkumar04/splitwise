package com.practice.splitwise.service;

import com.practice.splitwise.dto.CreateUserDTO;
import com.practice.splitwise.dto.UserDTO;
import com.practice.splitwise.model.User;
import com.practice.splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(CreateUserDTO createUserRequest) {
        String hashedPassword = passwordEncoder.encode(createUserRequest.getPassword());

        // Convert DTO to user object
        User user = User.from(createUserRequest, hashedPassword);

        User persistedUser = userRepository.save(user);
        return UserDTO.from(persistedUser);
    }

    public Optional<User> getUser(Long userId) {
        return userRepository.findById(userId);
    }

    public UserDTO updateUser(Long userId, CreateUserDTO createUserDTO) {
        if(!userRepository.existsById(userId)){
            return null;
        }

        User userToUpdate = User.from(createUserDTO, createUserDTO.getPassword());
        userToUpdate.setId(userId); // Consider Path Variable as source of truth

        User updateUser = userRepository.save(userToUpdate);

        return UserDTO.from(userToUpdate);


    }

    public boolean validateIds(Set<Long> userIds) {
        return true;
    }
}
