package com.example.demo.service;

import com.example.demo.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZW
 * @since 2022-09-05
 */
public interface ICommentService extends IService<Comment> {
    List<Comment> findCommentDetail(Integer forumId);
}
