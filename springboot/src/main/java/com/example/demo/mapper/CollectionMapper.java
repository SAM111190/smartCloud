package com.example.demo.mapper;

import com.example.demo.entity.Collection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ZW
 * @since 2023-02-11
 */
@Mapper
public interface CollectionMapper extends BaseMapper<Collection> {
    int insert(int userId,int questionId);
    int delete(int userId,int questionId);
    List<Question> selectAllcollections(int userId);

}
