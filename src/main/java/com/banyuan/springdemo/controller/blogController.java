package com.banyuan.springdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class blogController {
    @ResponseBody
    @GetMapping("blog/{blogId}")
    String getBlogByBlogId(@PathVariable(value = "blogId") Integer blogid){
        return "This blog is"+blogid;
    }
}
