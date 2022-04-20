package com.practice.splitwise.dto;

import lombok.Data;

@Data
public class CreateUserDTO {
    private String name;
    private String password;
    private String phoneNumber;
}
