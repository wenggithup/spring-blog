package com.banyuan.springdemo.controller;

import com.banyuan.springdemo.bean.Blog;
import com.banyuan.springdemo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class blogController {
    //依赖blogService
    @Autowired
    private BlogService blogService;

    //通过blogid展示用户博客,路径变量blogid
    @GetMapping("blog/{blogId}")
    String getBlogByBlogId(@PathVariable(value = "blogId") Integer id, Model model){
        //调用service层findBlogByBlogId获取blog对象
        Blog blog =blogService.findBlogByBlogId(id);
        //将blog对象存入域中，方便前台提取数据
        model.addAttribute ("blog",blog);
        //返回item.html页面
        return "item";
    }


    //通过用户姓名查询用户博客列表
    @GetMapping("user/{username}")
    String findBlogByUsername(@PathVariable(value = "username") String username){
    //调用serivce层方法，获得list集合
        List<Blog> list=blogService.findBlogListByUsername(username);
        return "";
    }
}
