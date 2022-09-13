package com.example.demo.mapper;

import com.example.demo.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ZW
 * @since 2022-09-05
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    @Select("select c.*,u.nick_name,u.avatar_url from comment c left join user u on c.user_id = u.id where c.forum_id = #{forumId}")
//    根据id排序
    List<Comment> findCommentDetail(@Param("forumId") Integer forumId);
}
