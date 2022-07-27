package com.example.demo.controller.dto;

import lombok.Data;
//接受前端登录请求
@Data
public class UserDTO {
    private String token;
    private Integer id;
    private String username;
    private String password;
    private String nick_name;
    private Integer age;
    private String sex;
    private String address;
    private String avatarUrl;
}
