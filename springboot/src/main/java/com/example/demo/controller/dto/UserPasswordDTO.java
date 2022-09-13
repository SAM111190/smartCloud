package com.example.demo.controller.dto;

import lombok.Data;

@Data
public class UserPasswordDTO {
    private String username;
    private String password;
    private String newPassword;
    private String oldPassword;
    private String address;
    private String code;
}
