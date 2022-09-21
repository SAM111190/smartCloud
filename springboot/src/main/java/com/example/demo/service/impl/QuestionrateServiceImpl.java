package com.example.demo.service.impl;

import com.example.demo.entity.Questionrate;
import com.example.demo.mapper.QuestionrateMapper;
import com.example.demo.service.IQuestionrateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JHJ
 * @since 2022-09-19
 */
@Service
public class QuestionrateServiceImpl extends ServiceImpl<QuestionrateMapper, Questionrate> implements IQuestionrateService {
@Resource
private QuestionrateMapper questionrateMapper;
    @Override
    public List<Questionrate> selectflag(Integer questionId, Integer userId)
{
    return questionrateMapper.selectflag(questionId,userId);
}
    @Override
    public boolean update(Questionrate questionrate)
    {
        return questionrateMapper.update(questionrate);
    }

    @Override
    public boolean updaterate(Questionrate questionrate)
    {
        return questionrateMapper.updaterate(questionrate);
    }

}
