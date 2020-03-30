package com.banyuan.springdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class indexController {
    @ResponseBody
    @GetMapping("/")
    //定义初始化页面
    String getIndex(){
        return "hello word!";
    }

}
