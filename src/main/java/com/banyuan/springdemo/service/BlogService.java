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

    //通过id 返回blog对象集合
    public Blog findBlogDetailByBlogId(Integer id) {
        return blogDao.selectBlogDetailByBlogId (id);
    }

    //新建博客
    public void insertBlogByCreate(Blog blog) {
         blogDao.insertBlogByCreate(blog);
    }


    //通过blogid删除blog
    public void deleteBlogByBlogId(Integer blogId) {
        blogDao.deleteBlogByBlogId(blogId);
    }

    //编辑博客
    public void updateBlog(String title, String content,Integer id) {
         blogDao.updateBlog(title,content,id);
    }

    //查询所有博客
    public List<Blog> findAllBlogs() {
        return blogDao.selectAllBlog();
    }

    public List<Blog> findBlogsByKeyword(String keyword) {
        return blogDao.selectBlogByKeyword(keyword);
    }
}
