package com.example.demo.mapper;

import com.example.demo.entity.Collection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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
}
