package com.banyuan.springdemo.service;

import com.banyuan.springdemo.bean.Comment;
import com.banyuan.springdemo.dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//声明此类交给spring管理
@Service
public class CommentService {
    //依赖于comentdao，使用spring的依赖注入
   @Autowired
    private CommentDao commentDao;

   //通过blog id查询评论集合
   public List<Comment> findCommentsByBlogId(Integer id){
       return commentDao.selectCommentsByBlogId (id);
    }
}
