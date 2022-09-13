package com.example.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.Constants;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.controller.dto.UserPasswordDTO;
import com.example.demo.entity.Menu;
import com.example.demo.entity.User;
import com.example.demo.entity.Validation;
import com.example.demo.exception.ServiceException;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.mapper.RoleMenuMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IMenuService;
import com.example.demo.service.IUserService;
import com.example.demo.service.IValidationService;
import com.example.demo.utils.TokenUtils;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.ReactiveListCommands;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 青哥哥
 * @since 2022-01-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")//拿到配置文件application里的用户邮箱
    private String from;

    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Resource
    private IMenuService menuService;
    @Resource
    private IValidationService validationService;
    @Override
    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, userDTO, true);
            // 设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            userDTO.setToken(token);

            String role = one.getRole(); // ROLE_ADMIN
            // 设置用户的菜单列表
            List<Menu> roleMenus = getRoleMenus(role);
            userDTO.setMenus(roleMenus);
            return userDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }

    @Override
    public UserDTO forget(UserDTO userDTO) {

        User one = getUserAddress(userDTO);


        if (one != null ) {
            BeanUtil.copyProperties(one, userDTO, true);
            // 设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            userDTO.setToken(token);
            return userDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名和邮箱不匹配");
        }
    }


    @Override
    public void updatePassword(UserPasswordDTO userPasswordDTO) {
        int update = userMapper.updatePassword(userPasswordDTO);
    }

    @Override
    public void changePassword(UserPasswordDTO userPasswordDTO) {
        int update = userMapper.changePassword(userPasswordDTO);
        if (update < 1) {
            throw new ServiceException(Constants.CODE_600, "密码错误");
        }
    }

    @Override
    public UserDTO loginAddress(UserDTO userDTO) {
        String address = userDTO.getAddress();
        String code = userDTO.getCode();
        QueryWrapper<Validation> validationQueryWrapper = new QueryWrapper<>();
        validationQueryWrapper.eq("address",address);
        validationQueryWrapper.eq("code",code);
        validationQueryWrapper.ge("time",new Date());//查询数据库没过期的code 数据库存入的是过期时间>=当前时间 则过期
        Validation one = validationService.getOne(validationQueryWrapper);
        if(one == null){
            throw new ServiceException("-1","验证码过期，请重新获取");
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("address", address);//根据邮箱查询用户信息
        User user = getOne(userQueryWrapper);
        if(user == null){
            throw new ServiceException("-1","未找到用户");
        }

        BeanUtil.copyProperties(user, userDTO, true);
        // 设置token
        String token = TokenUtils.genToken(user.getId().toString(), user.getPassword());
        userDTO.setToken(token);
        String role =user.getRole(); // ROLE_ADMIN
        // 设置用户的菜单列表
        List<Menu> roleMenus = getRoleMenus(role);
        userDTO.setMenus(roleMenus);
        return userDTO;
    }

    @Override
    public void sendAddressCode(String address) {
        Date now = new Date();
        //先查询同类型code
        QueryWrapper<Validation> validationQueryWrapper = new QueryWrapper<>();
        validationQueryWrapper.eq("address",address);
        validationQueryWrapper.ge("time",now); //查询数据库没过期的code
        Validation validation = validationService.getOne(validationQueryWrapper);
        if (validation != null){
            throw new ServiceException("-1","当前验证码仍然有效，请不要重复发送");
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);//发送人(必须写)
        message.setTo(address);//收件人
        message.setSubject("【智能云实验平台】忘记密码验证");//主题
        String code = RandomUtil.randomNumbers(4);//随机生成一个4位的验证码
        message.setText("您本次忘记密码的验证码是：" + code + "，有效期5分钟，请妥善保管，切勿泄露。");//内容
        message.setSentDate(now);//发送日期
        javaMailSender.send(message);

        //发送成功之后，把验证码村存到数据库
        validationService.saveCode(address,code, DateUtil.offsetMinute(now,5));//过期时间往后推5分钟
    }

    @Override
    public User register(UserDTO userDTO) {
        User one=getUserInfo(userDTO);
        if(one==null){
            one =new User();
            BeanUtil.copyProperties(userDTO,one,true);
            save(one);
        }else {
            throw new ServiceException(Constants.CODE_600, "用户已经存在");
        }
        return one;
    }
    private User getUserInfo(UserDTO userDTO)//重复代码写成函数，用于查询用户名以及密码
    {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        User one;
        try {
            one = getOne(queryWrapper);//从数据库查询用户信息

        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }
    private User getUserAddress(UserDTO userDTO)//重复代码写成函数，用于查询用户名以及密码
    {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("address", userDTO.getAddress());
        User one;
        try {
            one = getOne(queryWrapper);//从数据库查询用户信息

        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }
    /**
     * 获取当前角色的菜单列表
     * @param roleFlag
     * @return
     */
    private List<Menu> getRoleMenus(String roleFlag) {
        Integer roleId = roleMapper.selectByFlag(roleFlag);
        // 当前角色的所有菜单id集合
        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);

        // 查出系统所有的菜单(树形)
        List<Menu> menus = menuService.findMenus("");
        // new一个最后筛选完成之后的list
        List<Menu> roleMenus = new ArrayList<>();
        // 筛选当前用户角色的菜单
        for (Menu menu : menus) {
            if (menuIds.contains(menu.getId())) {
                roleMenus.add(menu);
            }
            List<Menu> children = menu.getChildren();
            // removeIf()  移除 children 里面不在 menuIds集合中的 元素
            children.removeIf(child -> !menuIds.contains(child.getId()));
        }
        return roleMenus;
    }
}
