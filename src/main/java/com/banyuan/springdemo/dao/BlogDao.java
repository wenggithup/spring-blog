package com.banyuan.springdemo.dao;

import com.banyuan.springdemo.bean.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogDao {
    //通过blogID，查询blog内容
    Blog selectBlogByBlogId(Integer id);

    //通过username返回博客集合
    List<Blog> selectBlogListByUsername(String username);

    //通过blog id返回 对应的comment对象和user对象
    Blog selectBlogDetailByBlogId(Integer id);

    void insertBlogByCreate(Blog blog);

    void deleteBlogByBlogId(Integer blogId);

    void updateBlog(String title, String content,Integer id);
}
