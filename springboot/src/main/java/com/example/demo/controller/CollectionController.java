package com.example.demo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.CollectionMapper;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Result;

import com.example.demo.service.ICollectionService;
import com.example.demo.entity.Collection;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZW
 * @since 2023-02-11
 */
@RestController
@RequestMapping("//collection")
        public class CollectionController {
    
@Resource
private ICollectionService collectionService;
@Resource
private CollectionMapper collectionMapper;

// 新增或者更新
@PostMapping
public Result save(@RequestBody Collection collection) {
    collectionService.saveOrUpdate(collection);
        return Result.success();
        }

@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id) {
    collectionService.removeById(id);
        return Result.success();
        }

@PostMapping("/del/batch")
public Result deleteBatch(@RequestBody List<Integer> ids) {
    collectionService.removeByIds(ids);
        return Result.success();
        }

@GetMapping
public Result findAll() {
        return Result.success(collectionService.list());
        }

@GetMapping("/{id}")
public Result findOne(@PathVariable Integer id) {
        return Result.success(collectionService.getById(id));
        }

@GetMapping("/page")
public Result findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(collectionService.page(new Page<>(pageNum, pageSize), queryWrapper));
        }
        @PostMapping("/insert")
    public Result insert(@RequestParam Integer userId, @RequestParam Integer questionId) {
        return Result.success(collectionMapper.insert(userId,questionId));
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer userId, @RequestParam Integer questionId) {
        return Result.success(collectionMapper.delete(userId,questionId));
    }
    @GetMapping("/findall/{userId}")
    public Result findAll(@PathVariable Integer userId) {
        return Result.success(collectionMapper.selectAllcollections(userId));
    }

}

