package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Wrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserMapper userMapper;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<?> login(@RequestBody User user)//从前端获取数据
    {
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()).eq(User::getPassword,user.getPassword()));//进行唯一查找
        if(res == null ){
            return Result.error("-1","用户名或密码错误");
        }
        return Result.success(res);
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user)//从前端获取数据
    {
       User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()));
        if(res != null ){
            return Result.error("-1","用户名重复");
        }
        if(user.getPassword() == null){  //如果没有输入密码，默认123456
            user.setPassword("123456");
        }
        userMapper.insert(user);
        return Result.success();
    }
    @GetMapping("/find")
    public List<User> index()//查询
    {
        return userService.list();
    }
    @PostMapping
    public boolean save(@RequestBody User user)
    {
        //用户新增或者更新
        return userService.saveUser(user);
    }
    @DeleteMapping("/{id}")//删除
    public boolean delete(@PathVariable Integer id)
    {
        return userService.removeById(id);
    }
    @GetMapping("/page")//分页查询
    public  IPage<User>  findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestParam(defaultValue = "") String username)
    {
        IPage<User> page=new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        if (!"".equals(username))
        {
            queryWrapper.like("username",username);
        }
        queryWrapper.orderByDesc("id");
        IPage<User> userPage=userService.page(page,queryWrapper);
        return userPage;
    }
//    public  Map<String,Object> findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestParam String username)
//    {
//       pageNum=(pageNum -1)*pageSize;
//       username="%"+username+"%";
//       List<User> data= userMapper.selectPage(pageNum,pageSize,username);
//       Integer total=userMapper.selectTotal(username);
//       Map<String,Object> res=new HashMap<>();
//       res.put("data",data);
//       res.put("total",total);
//       return res;
//    }
//    @GetMapping
    //分页查询
//    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize,@RequestParam(defaultValue = "") String search)
//    {
//        LambdaQueryWrapper<User> wrapper=Wrappers.<User>lambdaQuery();
//        if (StrUtil.isNotBlank(search))
//        {
//            wrapper.like(User::getNickName,search);
//        }
//        Page<User> userPage= userMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
//        return Result.success( userPage);
//    }
//    @PutMapping
    //用户更新
//    public Result<?> update(@RequestBody User user)
//    {
//        userMapper.updateById(user);
//        return Result.success();
//    }
//    @DeleteMapping("/{id}")
    //用户删除
//    public Result<?> update(@PathVariable Long id)
//    {
//        userMapper.deleteById(id);
//        return Result.success();
//    }

}
