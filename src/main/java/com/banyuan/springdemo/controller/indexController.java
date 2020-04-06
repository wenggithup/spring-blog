package com.banyuan.springdemo.controller;

import com.banyuan.springdemo.bean.Blog;
import com.banyuan.springdemo.service.BlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class indexController {
    //依赖blogService
    @Autowired
    private BlogService blogService;

    @GetMapping(value = {"/","/homepage"})
    //定义初始化页面
    String getIndex(@RequestParam Optional<Integer> page,
                    @RequestParam Optional<Integer> size,
                    Model model){
        //前端页面需要blogs，并且需要分页插件
        PageHelper.startPage (page.orElse (1),size.orElse (10),"id asc");
        //调用方法获取所有的blog
        List<Blog> blogs=blogService.findAllBlogs();
        //将查询出来的结果放入pageInfo中，并返回对象
        PageInfo pageInfo=new PageInfo (blogs);
        //将pageInfo放入modle域中
        model.addAttribute ("blogs",pageInfo);
        return "homepage";
    }

}
