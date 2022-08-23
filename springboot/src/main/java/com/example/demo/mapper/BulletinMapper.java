package com.example.demo.mapper;

import com.example.demo.entity.Bulletin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Forum;
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
public interface BulletinMapper extends BaseMapper<Bulletin> {

    @Insert("INSERT into bulletin(time,username,content,avatar_url,href,likes,title,nick_name)VALUES (#{time},#{username},#{content},#{avatarUrl},#{href},#{likes},#{title},#{nickName})")
    int insert(Bulletin bulletin);
}
