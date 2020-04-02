package com.banyuan.springdemo.controller;

import com.banyuan.springdemo.bean.User;
import com.banyuan.springdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class loginController {
    @Autowired
    private UserService userService;

    //登陆界面
    @GetMapping("/login")
    String loginShow(){
        return "login";
    }
    @PostMapping("/login")
    String loginCheck(@RequestParam String username,
                      @RequestParam String password,
                      @RequestParam(required = false,value = "next") String next,
                      HttpSession session){
        User user = userService.selectUserByUsername (username);
        if(user!=null) {
            //1、判断用户名密码是否正确
            if (user.getPassword ().equals (password)){
                //将user对象放入session域中
                session.setAttribute ("USER",user);
                //2如果正确，判断next是否为空
                if (next != null) {
                    //3、如果不为空，跳转到刚刚的界面
                    return "redirect:".concat (next);
                } else {
                    //4、如果为空，则跳转到admin界面中
                    return "redirect:/admin";
                }
            }else{
                return "redirect:/login";
            }
        }else {
            return "redirect:/login";
        }

    }

}
