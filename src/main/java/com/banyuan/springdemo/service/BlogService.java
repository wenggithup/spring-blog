package com.banyuan.springdemo.service;

import com.banyuan.springdemo.bean.Blog;
import com.banyuan.springdemo.dao.BlogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogDao blogDao;

    //通过blogid查询blog对象
    public Blog findBlogByBlogId(Integer id) {
        //调用dao层方法，返回Blog对象
       return  blogDao.selectBlogByBlogId (id);
    }
    //通过用户姓名，返回Blog对象集合
    public List<Blog> findBlogListByUsername(String username) {
        return blogDao.selectBlogListByUsername(username);
    }
}
