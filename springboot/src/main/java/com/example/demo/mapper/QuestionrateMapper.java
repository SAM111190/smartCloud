package com.example.demo.mapper;

import com.example.demo.entity.Forum;
import com.example.demo.entity.Questionrate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JHJ
 * @since 2022-09-19
 */
@Mapper
public interface QuestionrateMapper extends BaseMapper<Questionrate> {
    @Insert("INSERT into questionrate(user_id,rate,question_id,time)VALUES (#{userId},#{rate},#{questionId},#{time})")
    int insert(Questionrate questionrate);
List<Questionrate> selectflag(Integer questionId, Integer userId);
boolean update(Questionrate questionrate);
boolean updaterate(Questionrate questionrate);
}
