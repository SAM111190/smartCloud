package com.example.demo.service;

import cn.hutool.core.date.DateTime;
import com.example.demo.entity.Validation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZW
 * @since 2022-09-13
 */
public interface IValidationService extends IService<Validation> {

    void saveCode(String address, String code, DateTime expireDate);
}
