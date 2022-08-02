package com.example.demo.service.impl;

import com.example.demo.entity.Question;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.service.IQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JHJ
 * @since 2022-08-01
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

}
