package com.example.demo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Result;

import com.example.demo.service.IForumService;
import com.example.demo.entity.Forum;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZW
 * @since 2022-08-19
 */
@RestController
@RequestMapping("//forum")
        public class ForumController {
    
@Resource
private IForumService forumService;

// 新增或者更新
@PostMapping
public Result save(@RequestBody Forum forum) {
    forumService.saveOrUpdate(forum);
        return Result.success();
        }

@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id) {
    forumService.removeById(id);
        return Result.success();
        }

@PostMapping("/del/batch")
public Result deleteBatch(@RequestBody List<Integer> ids) {
    forumService.removeByIds(ids);
        return Result.success();
        }

@GetMapping
public Result findAll() {
        return Result.success(forumService.list());
        }

@GetMapping("/{id}")
public Result findOne(@PathVariable Integer id) {
        return Result.success(forumService.getById(id));
        }

    @GetMapping("/page")//分页查询
    public IPage<Forum> findPage(@RequestParam  (defaultValue = "1") Integer pageNum,
                                @RequestParam (defaultValue = "3") Integer pageSize,
                                @RequestParam(defaultValue = "") String username,
                                @RequestParam(defaultValue = "") String address)
    {
        IPage<Forum> page=new Page<>(pageNum,pageSize);
        QueryWrapper<Forum> queryWrapper=new QueryWrapper<>();
        if (!"".equals(username))
        {
            queryWrapper.like("username",username);
        }
//        if (!"".equals(address))
//        {
//            queryWrapper.like("address",address);
//        }
        queryWrapper.orderByDesc("likes");
        IPage<Forum> forumPage=forumService.page(page,queryWrapper);
        return forumPage;
    }

        }

