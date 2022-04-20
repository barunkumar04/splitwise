package com.practice.splitwise.model;

import com.practice.splitwise.dto.CreateUserDTO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "USERS")
public class User extends BaseModel{

    private String name;
    private String email;
    private String phoneNo;
    private String hashedPassword;

    // Static initializer
    public static User from(CreateUserDTO userDTO, String hashedPassword){
        User user = new User();
        user.setName(userDTO.getName());
        user.setPhoneNo(userDTO.getPhoneNumber());
        user.setHashedPassword(hashedPassword);
        return user;
    }

}
