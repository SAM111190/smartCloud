package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Course;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
    CourseMapper courseMapper;
    @Autowired
    CourseService courseService;

    @PostMapping
    //新增
    public Result save(@RequestBody Course course)
    {
        courseMapper.insert(course);
        return Result.success();
    }
    @GetMapping
    //分页查询
    public Result findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search)
    {
        LambdaQueryWrapper<Course> wrapper=Wrappers.<Course>lambdaQuery();
        if (StrUtil.isNotBlank(search))
        {
            wrapper.like(Course::getName,search);
        }
        Page<Course> coursePage=courseMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        return Result.success(coursePage);
    }
    @PutMapping
    //更新
    public Result update(@RequestBody Course course)
    {
        courseMapper.updateById(course);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    //用户删除
    public Result update(@PathVariable Long id)
    {
        courseMapper.deleteById(id);
        return Result.success();
    }

}
