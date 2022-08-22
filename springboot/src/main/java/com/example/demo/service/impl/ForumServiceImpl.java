package com.example.demo.service.impl;

import com.example.demo.entity.Forum;
import com.example.demo.mapper.ForumMapper;
import com.example.demo.service.IForumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZW
 * @since 2022-08-19
 */
@Service
public class ForumServiceImpl extends ServiceImpl<ForumMapper, Forum> implements IForumService {

}
