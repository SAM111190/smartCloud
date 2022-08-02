package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.entity.Dict;
import com.example.demo.entity.Menu;
import com.example.demo.mapper.DictMapper;
import com.example.demo.service.IMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JHJ
 * @since 2022-07-29
 */
@RestController
@RequestMapping("//menu")
        public class MenuController {

@Resource
private IMenuService menuService;
@Resource
private DictMapper dictMapper;

// 新增或者更新
@PostMapping
public Result save(@RequestBody Menu menu) {
    menuService.saveOrUpdate(menu);
        return Result.success();
        }

@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id) {
    menuService.removeById(id);
        return Result.success();
        }

@PostMapping("/del/batch")
public Result deleteBatch(@RequestBody List<Integer> ids) {
    menuService.removeByIds(ids);
        return Result.success();
        }
//    @GetMapping
//    public Result findAll() {
//        return Result.success(menuService.list());
//    }
@GetMapping
public Result findAll(@RequestParam(defaultValue = "") String name) {

       return Result.success(menuService.findMenus(name));
}

@GetMapping("/{id}")
public Result findOne(@PathVariable Integer id) {
        return Result.success(menuService.getById(id));
        }

@GetMapping("/page")
public Result findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize,
 @RequestParam String name) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
    queryWrapper.like("name", name);
    queryWrapper.orderByDesc("id");
        return Result.success(menuService.page(new Page<>(pageNum, pageSize), queryWrapper));
        }
    @GetMapping("/icons")
    public Result getIcons() {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", Constants.DICT_TYPE_ICON);
        return Result.success(dictMapper.selectList(queryWrapper));
    }

}

