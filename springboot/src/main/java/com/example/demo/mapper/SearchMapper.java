package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper extends BaseMapper<Search> {
    List<Question> searchQuestion(String name);
    List<User> searchUser(String name);
    List<Forum> searchForum(String name);
    List<Bulletin> searchBulletin(String name);
}
