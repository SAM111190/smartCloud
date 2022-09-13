package com.example.demo.controller.dto;

import com.example.demo.entity.Menu;
import lombok.Data;

import java.util.List;

//接受前端登录请求
@Data
public class UserDTO {
    private String token;
    private Integer id;
    private String username;
    private String password;
    private String nickName;
    private String birthday;
    private String sex;
    private String address;
    private String code;
    private String telephone;
    private String introduce;
    private String avatarUrl;
    private String role;
    private List<Menu> menus;
}
