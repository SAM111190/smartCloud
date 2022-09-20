package com.example.demo.service;

import com.example.demo.entity.Questionrate;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JHJ
 * @since 2022-09-19
 */
public interface IQuestionrateService extends IService<Questionrate> {
    List<Questionrate> selectflag(Integer questionId, Integer userId);
    boolean update(Questionrate questionrate);
}
