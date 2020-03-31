package com.banyuan.springdemo.dao;

import com.banyuan.springdemo.bean.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogDao {
    //通过blogID，查询blog内容
    Blog selectBlogByBlogId(Integer id);

    List<Blog> selectBlogListByUsername(String username);

}
