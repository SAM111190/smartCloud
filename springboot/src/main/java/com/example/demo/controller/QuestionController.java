package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Question;
import com.example.demo.entity.User;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Resource
    QuestionMapper questionMapper;

    @PostMapping
    //新增
    public Result<?> save(@RequestBody Question question)
    {
        questionMapper.insert(question);
        return Result.success();
    }
    @GetMapping
    //分页查询
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search)
    {
        LambdaQueryWrapper<Question> wrapper=Wrappers.<Question>lambdaQuery();
        if (StrUtil.isNotBlank(search))
        {
            wrapper.like(Question::getName,search);
        }
        Page<Question> questionPage= questionMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        return Result.success( questionPage);
    }
    @PutMapping
    //更新
    public Result<?> update(@RequestBody Question question)
    {
        questionMapper.updateById(question);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    //用户删除
    public Result<?> update(@PathVariable Long id)
    {
        questionMapper.deleteById(id);
        return Result.success();
    }
}
