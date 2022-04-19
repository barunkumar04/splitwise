package com.practice.splitwise.model;

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

}
