package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.*;
import com.example.demo.mapper.SearchMapper;
import com.example.demo.service.IQuestionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JHJ
 * @since 2022-08-01
 */
@RestController
@RequestMapping("//search")
public class SearchController {

    @Resource
    SearchMapper searchMapper;
    @GetMapping()//分页查询
    public Search findPage(@RequestParam(defaultValue = "") String name)
    {
        Search res=new Search();
        name = "%"+ name +"%";
        res.questions=searchMapper.searchQuestion(name);
        res.users=searchMapper.searchUser(name);
        res.forums=searchMapper.searchForum(name);
        res.bulletins=searchMapper.searchBulletin(name);
        return res;
    }
    @GetMapping("/question")//分页查询
    public List<Question> searchQuestion(@RequestParam(defaultValue = "") String name)
    {
        name = "%"+ name +"%";
        List<Question> res=new ArrayList<>();
        res=searchMapper.searchQuestion(name);
        return res;
    }

    @GetMapping("/user")//分页查询
    public List<User> searchUser(@RequestParam(defaultValue = "") String name)
    {
        name = "%"+ name +"%";
        List<User> res=new ArrayList<>();
        res=searchMapper.searchUser(name);
        return res;
    }
    @GetMapping("/forum")//分页查询
    public List<Forum> searchForum(@RequestParam(defaultValue = "") String name)
    {
        name = "%"+ name +"%";
        List<Forum> res=new ArrayList<>();
        res=searchMapper.searchForum(name);
        return res;
    }

    @GetMapping("/bulletin")//分页查询
    public List<Bulletin> searchBulletin(@RequestParam(defaultValue = "") String name)
    {
        name = "%"+ name +"%";
        List<Bulletin> res=new ArrayList<>();
        res=searchMapper.searchBulletin(name);
        return res;
    }

}

