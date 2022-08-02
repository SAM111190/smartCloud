package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JHJ
 * @since 2022-07-29
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> findMenus(String name);
}
