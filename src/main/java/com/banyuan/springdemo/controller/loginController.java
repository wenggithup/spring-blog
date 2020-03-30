package com.banyuan.springdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {

    //管理登陆界面
    @GetMapping("/login")
    String loginShow(){
        return "login";
    }
}
