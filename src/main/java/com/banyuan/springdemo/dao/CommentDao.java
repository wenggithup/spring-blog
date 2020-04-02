package com.banyuan.springdemo.dao;

import com.banyuan.springdemo.bean.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
//声明此类交给spring管理
@Repository

public interface CommentDao {
    //通过blog id查询评论集合
    List<Comment> selectCommentsByBlogId(Integer id);
}
