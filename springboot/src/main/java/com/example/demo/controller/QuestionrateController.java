package com.example.demo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Forum;
import com.example.demo.mapper.QuestionrateMapper;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Result;

import com.example.demo.service.IQuestionrateService;
import com.example.demo.entity.Questionrate;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JHJ
 * @since 2022-09-19
 */
@RestController
@RequestMapping("//questionrate")
        public class QuestionrateController {
    
@Resource
private IQuestionrateService questionrateService;
    @Resource
    private QuestionrateMapper  questionrateMapper;

// 新增或者更新
@PostMapping
public Result save(@RequestBody Questionrate questionrate) {
    String current = new SimpleDateFormat("yyyy.MM.dd HH:mm").format( new Date());
    questionrate.setTime(current);
    questionrateService.saveOrUpdate(questionrate);
        return Result.success();
        }
    @PostMapping("/update")
    public Result update(@RequestBody Questionrate questionrate) {
        String current = new SimpleDateFormat("yyyy.MM.dd HH:mm").format( new Date());
        questionrate.setTime(current);
        questionrateService.update(questionrate);
        return Result.success();
    }

@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id) {
    questionrateService.removeById(id);
        return Result.success();
        }

@PostMapping("/del/batch")
public Result deleteBatch(@RequestBody List<Integer> ids) {
    questionrateService.removeByIds(ids);
        return Result.success();
        }

@GetMapping
public Result findAll() {
        return Result.success(questionrateService.list());
        }

@GetMapping("/{id}")
public Result findOne(@PathVariable Integer id) {
        return Result.success(questionrateService.getById(id));
        }

@GetMapping("/page")
public Result findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        QueryWrapper<Questionrate> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(questionrateService.page(new Page<>(pageNum, pageSize), queryWrapper));
        }
    @PostMapping("/insert")
    public Result insert(@RequestBody Questionrate questionrate) {

        String current = new SimpleDateFormat("yyyy.MM.dd HH:mm").format( new Date());
        questionrate.setTime(current);
        questionrateMapper.insert(questionrate);
        return Result.success();
    }
    @GetMapping("/{questionId}/{userId}")
    public Result selectflag(@PathVariable Integer questionId, @PathVariable Integer userId) {

    return Result.success(questionrateService.selectflag(questionId,userId));

    }


}

