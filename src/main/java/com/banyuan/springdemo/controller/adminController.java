package com.banyuan.springdemo.controller;

import com.banyuan.springdemo.bean.Blog;
import com.banyuan.springdemo.bean.User;
import com.banyuan.springdemo.service.BlogService;
import com.banyuan.springdemo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class adminController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    //对博客进行编辑或删除
    @GetMapping("/admin/blogs")
    String showAdmin(Principal principal,
                     @RequestParam Optional<Integer> page,
                     @RequestParam Optional<Integer> size,
                     Model model){
        //1、将USER值取出
        String name = principal.getName ();
        User user = userService.selectUserByUsername (name);
        PageHelper.startPage (page.orElse (1),size.orElse (10));
        //2、通过user对象，查找并分页blog对象
        List<Blog> blogs = blogService.findBlogListByUsername (user.getName ());
        PageInfo <Blog> pageInfo=new PageInfo<> (blogs);
        //3、将blogs,username存入model域中
        model.addAttribute ("blogs",pageInfo);
        model.addAttribute ("username",user.getName ());
        return "admin-blogs";
    }
    //admin 界面
    @GetMapping("/admin")
    String showAdmin(Principal principal, Model model){
        //1、将USER值取出
        String name = principal.getName ();
        User user = userService.selectUserByUsername (name);
        model.addAttribute ("user",user);
        return "admin";
    }


    //注销页面
    @GetMapping("/logout")
    String logout(HttpSession session){
        session.removeAttribute ("USER");
        return "redirect:/homepage";
    }
}
