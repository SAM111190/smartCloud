package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.common.Result;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.controller.dto.UserPasswordDTO;
import com.example.demo.entity.User;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 青哥哥
 * @since 2022-01-26
 */
public interface IUserService extends IService<User> {

  UserDTO login(UserDTO userDTO);

  UserDTO forget(UserDTO userDTO);

  User register(UserDTO userDTO);

  void updatePassword(UserPasswordDTO userPasswordDTO);

  void changePassword(UserPasswordDTO userPasswordDTO);
}
