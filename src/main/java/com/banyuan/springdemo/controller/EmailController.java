package com.banyuan.springdemo.controller;

import com.banyuan.springdemo.service.EmailService;
import com.banyuan.springdemo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class EmailController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private RedisService redisService;

    //通过username发送邮件（后期放在admin页面中）
    @ResponseBody
    @RequestMapping(value = "/mail/send",method= RequestMethod.POST)
    String sendEmail(Principal principal){
        String username = principal.getName ();
        emailService.sendMailActive (username);
        return "邮件已发送";

    }
    //处理用户点击链接
    @ResponseBody
    @RequestMapping(value = "/active",method = RequestMethod.GET)
    String activeAcount(@RequestParam String token){
        //从redis获取键值对，如果存在则表示当前用户已经激活成功，否则没有该用户
        String username = redisService.getUsernameByToken (token);
        if(username!=null){
            return "激活成功";
        }else {
            return "用户不存在";
        }
    }


}
