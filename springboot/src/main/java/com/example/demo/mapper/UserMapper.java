package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.controller.dto.UserPasswordDTO;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper

public interface UserMapper extends BaseMapper<User> {
//    @Select("SELECT * from user")
//    List<User> findAll();
//    @Insert("INSERT into user(username,password,nick_name,age,sex,address)VALUES (#{username},#{password},#{nick_name},#{age},#{sex},#{address})")
//    int insert(User user);
//    @Update("update user set username=#{username}, password=#{password}, nick_name=#{nick_name},age=#{age}, sex=#{sex},address=#{address} where id=#{id}")
//    int update(User user);
//    @Delete("delete from user where id=#{id}")
//    Integer deleteById(@Param("id") Integer id);
//    @Select("select * from user where username like #{username} limit #{pageNum},#{pageSize}")
//    List<User> selectPage(Integer pageNum,Integer pageSize,String username);
//    @Select("select count(*) from user")
//    Integer selectTotal(String username);

    @Update("update user set password = #{password} where username = #{username} ")
    int updatePassword(UserPasswordDTO userPasswordDTO);
}
