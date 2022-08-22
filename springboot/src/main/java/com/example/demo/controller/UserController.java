package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.controller.dto.UserPasswordDTO;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserService;
import com.example.demo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserMapper userMapper;
    @Autowired
    private IUserService userService;

    //    @PostMapping("/login")
//    public Result<?> login(@RequestBody User user)//从前端获取数据
//    {
//        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()).eq(User::getPassword,user.getPassword()));//进行唯一查找
//        if(res == null ){
//            return Result.error("-1","用户名或密码错误");
//        }
//        return Result.success(res);
//    }
    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO)
    {
        String username=userDTO.getUsername();
        String password=userDTO.getPassword();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password))
        {
            return Result.error(Constants.CODE_400,"参数错误");
        }
        UserDTO dto=userService.login(userDTO);
        return Result.success(dto);
    }
    @PostMapping("/forget")
    public Result forget(@RequestBody UserDTO userDTO)
    {
        String username=userDTO.getUsername();
        String address=userDTO.getAddress();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(address))
        {
            return Result.error(Constants.CODE_400,"参数错误");
        }
        UserDTO dto=userService.forget(userDTO);
        return Result.success(dto);
    }
    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO)//从前端获取数据
    {
        String username=userDTO.getUsername();
        String password=userDTO.getPassword();
        String address=userDTO.getAddress();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password))
        {
            return Result.error(Constants.CODE_400,"参数错误");
        }
        if( userDTO.getAvatarUrl()==null)
        {
            userDTO.setAvatarUrl("https://inews.gtimg.com/newsapp_bt/0/14881026322/1000");
        }
        if( userDTO.getRole()==null)
        {
            userDTO.setRole("ROLE_USER");
        }
        if( userDTO.getSex()==null)
        {
            userDTO.setSex("保密");
        }
        return Result.success(userService.register(userDTO));
    }
    @GetMapping("/find")
    public List<User> index()//查询
    {
        return userService.list();
    }
    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody User user) {
        if (user.getId() == null && user.getPassword() == null) {  // 新增用户默认密码
            user.setPassword("123456");
        }
        if(user.getId() == null && user.getAvatarUrl()==null)
        {
            user.setAvatarUrl("https://inews.gtimg.com/newsapp_bt/0/14881026322/1000");
        }
        return Result.success(userService.saveOrUpdate(user));
    }

    @PostMapping("/password")
    public Result password(@RequestBody UserPasswordDTO userPasswordDTO) {
        userService.updatePassword(userPasswordDTO);
        return Result.success();
    }


    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(userService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(userService.removeByIds(ids));
    }

    @GetMapping
    public Result findAll() {
        return Result.success(userService.list());
    }

    @GetMapping("/role/{role}")
    public Result findUsersByRole(@PathVariable String role) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role", role);
        List<User> list = userService.list(queryWrapper);
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(userService.getById(id));
    }

    @GetMapping("/username/{username}")
    public Result findByUsername(@PathVariable String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return Result.success(userService.getOne(queryWrapper));
    }

    @GetMapping("/page")//分页查询
    public  IPage<User>  findPage(@RequestParam   Integer pageNum,
                                  @RequestParam  Integer pageSize,
                                  @RequestParam(defaultValue = "") String username,
                                  @RequestParam(defaultValue = "") String address)
    {
        IPage<User> page=new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        if (!"".equals(username))
        {
            queryWrapper.like("username",username);
        }
//        if (!"".equals(address))
//        {
//            queryWrapper.like("address",address);
//        }
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
