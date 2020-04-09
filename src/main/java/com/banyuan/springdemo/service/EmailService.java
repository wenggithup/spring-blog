package com.banyuan.springdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    //发送文件到指定用户的邮箱
    public void sendMailActive(String username){
        String msg="您已注册，请点击下链接激活邮箱";
        String url="http://localhost:8080/active?token=";
        String token=new Date ().getTime ()+"";
        url+=token;
        //1、拼接邮箱信息
        msg+=url;
        //将信息存入redis中
        redisService.setTokenInRedis (token,username,60*30);
        //2、获取发送邮箱地址(测试使用真实email，此处不使用）
        String email=userService.selectUserByUsername (username).getEmail ();
        //3、构造email
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage ();
        simpleMailMessage.setFrom ("1771218519@qq.com");
        simpleMailMessage.setTo (email);
        simpleMailMessage.setSubject ("blog激活");
        simpleMailMessage.setText (msg);
        //4、发送email
        mailSender.send (simpleMailMessage);

    }

}
