package com.example.demo.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.entity.Validation;
import com.example.demo.mapper.ValidationMapper;
import com.example.demo.service.IValidationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.jdbc.object.UpdatableSqlQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZW
 * @since 2022-09-13
 */
@Service
public class ValidationServiceImpl extends ServiceImpl<ValidationMapper, Validation> implements IValidationService {
    @Transactional
    @Override
    public void saveCode(String address, String code, DateTime expireDate){
        Validation validation = new Validation();
        validation.setAddress(address);
        validation.setCode(code);
        validation.setTime(expireDate);
        //先删除
        UpdateWrapper<Validation> validationUpdateWrapper = new UpdateWrapper<>();
        validationUpdateWrapper.eq("address",address);
        remove(validationUpdateWrapper);
        //再插入新的code
        save(validation);
    }
}
