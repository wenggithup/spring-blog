package com.banyuan.springdemo.controller;

import com.banyuan.springdemo.bean.Blog;
import com.banyuan.springdemo.service.BlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class searchController {
    //依赖blogService
    @Autowired
    private BlogService blogService;

    //通过参数查找对应的值并返回
    @GetMapping("/search")
    String getResultBySearch(@RequestParam Optional<Integer> page,
                             @RequestParam Optional<Integer> size,
                             HttpSession session,Model model){
        String keyword =(String) session.getAttribute ("KEYWORD");
        getBlogPageInfo (keyword,page,size,model);
        session.removeAttribute ("KEYWORD");
        return "homepage";
    }

    //搜索post方式
    @PostMapping("/search")
    String getResultByKeyword(@RequestParam(value = "keyword") String keyword,
                              @RequestParam Optional<Integer> page,
                              @RequestParam Optional<Integer> size,
                              Model model, HttpSession session){
        getBlogPageInfo (keyword, page, size, model);
        session.setAttribute ("KEYWORD",keyword);
        return "homepage";
    }


    @GetMapping("/UserSearch")
    String getUserResultBykeyword(@RequestParam Optional<Integer> page,
                                  @RequestParam Optional<Integer> size,
                                  Model model, HttpSession session){
        String keyword =(String) session.getAttribute ("USERKEYWORD");
        List<Blog> blogs=blogService.findBlogsByKeyword(keyword);

        getBlogPageInfo (keyword,page,size,model);

        session.removeAttribute ("USERKEYWORD");

        return "list";
    }

    @PostMapping("/UserSearch")
    String getUserResult(@RequestParam(value = "keyword") String keyword,
                         @RequestParam Optional<Integer> page,
                         @RequestParam Optional<Integer> size,
                         Model model, HttpSession session){
        List<Blog> blogs=blogService.findBlogsByKeyword(keyword);

        getBlogPageInfo (keyword,page,size,model);

        session.setAttribute ("USERKEYWORD",keyword);
        return "list";
    }

    //抽取公共方法
    private void getBlogPageInfo(String keyword,Optional<Integer> page,
                                           Optional<Integer> size,Model model){
        PageHelper.startPage (page.orElse (1),size.orElse (10));
        List<Blog> blogs=blogService.findBlogsByKeyword(keyword);

        PageInfo <Blog> pageInfo=new PageInfo<> (blogs);

        model.addAttribute ("blogs",pageInfo);

    }
}
