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

    //创建博客
    void insertBlogByCreate(Blog blog);

    //通过blogid删除blog
    void deleteBlogByBlogId(Integer blogId);

    //更新blog
    void updateBlog(String title, String content,Integer id);

    //查询所有blog集合，根据id
    List<Blog> selectAllBlog();

    List<Blog> selectBlogByKeyword(String keyword);
}
