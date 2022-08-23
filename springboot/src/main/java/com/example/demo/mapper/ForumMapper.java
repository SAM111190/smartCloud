package com.example.demo.mapper;

import com.example.demo.entity.Forum;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ZW
 * @since 2022-08-19
 */
@Mapper
public interface ForumMapper extends BaseMapper<Forum> {
    @Insert("INSERT into forum(username,role,content,avatar_url,area,time,likes,title,nick_name)VALUES (#{username},#{role},#{content},#{avatarUrl},#{area},#{time},#{likes},#{title},#{nickName})")
    int insert(Forum forum);

}
