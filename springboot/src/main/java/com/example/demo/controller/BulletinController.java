package com.example.demo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Forum;
import com.example.demo.mapper.BulletinMapper;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Result;

import com.example.demo.service.IBulletinService;
import com.example.demo.entity.Bulletin;

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
@RequestMapping("//bulletin")
        public class BulletinController {
    
@Resource
private IBulletinService bulletinService;
@Resource
private BulletinMapper bulletinMapper;

// 新增或者更新
@PostMapping
public Result save(@RequestBody Bulletin bulletin) {
    bulletinService.saveOrUpdate(bulletin);
        return Result.success();
        }

@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id) {
    bulletinService.removeById(id);
        return Result.success();
        }

@PostMapping("/del/batch")
public Result deleteBatch(@RequestBody List<Integer> ids) {
    bulletinService.removeByIds(ids);
        return Result.success();
        }

@GetMapping
public Result findAll() {
        return Result.success(bulletinService.list());
        }

@GetMapping("/{id}")
public Result findOne(@PathVariable Integer id) {
        return Result.success(bulletinService.getById(id));
        }

    @GetMapping("/page")//分页查询
    public IPage<Bulletin> findPage(@RequestParam  (defaultValue = "1") Integer pageNum,
                                 @RequestParam (defaultValue = "3") Integer pageSize,
                                 @RequestParam(defaultValue = "") String username,
                                 @RequestParam(defaultValue = "") String address)
    {
        IPage<Bulletin> page=new Page<>(pageNum,pageSize);
        QueryWrapper<Bulletin> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("time");
        IPage<Bulletin> bulletinPage=bulletinService.page(page,queryWrapper);
        return bulletinPage;
    }

    @PostMapping("/insert")
    public Result insert(@RequestBody Bulletin bulletin) {

        String current = new SimpleDateFormat("yyyy/MM/dd ").format( new Date());
        bulletin .setTime(current);
        bulletinMapper.insert(bulletin);
        return Result.success();
    }

}

