package com.banyuan.springdemo.controller;

import com.banyuan.springdemo.bean.User;
import com.banyuan.springdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Date;

@Controller
public class fileController {
    @Autowired
    private UserService userService;
    private final ResourceLoader resourceLoader;

    //定义本机目录
    String path="/Users/edz/";

    public fileController(@Autowired  ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    //处理upload请求
    @PostMapping("/upload")
    String getFile(@RequestParam(required = false,value = "name") String name,
                   @RequestParam MultipartFile file,
                   Principal principal, Model model) throws IOException {
        //解决用户上传重名
        Date date=new Date ();
        long time = date.getTime ();
        name=name+time;
        //1、将文件拷贝到本地
        Path avatarPath= Paths.get (path,name);
        Files.copy (file.getInputStream (), avatarPath);
        //2、将文件名称存储到数据库中
        //2、1获取当前用户,并将头像名称导入db
        String username = principal.getName ();
        User user =userService.selectUserByUsername (username);
        user.setAvatar (avatarPath.toString ());
        userService.setUserByAvatar (user.getId (),avatarPath.toString ());
        //将user 对象放入域中
        model.addAttribute ("user",user);
        return "/admin";
    }
    @ResponseBody
    @GetMapping("/dyImage/{filename}")
    ResponseEntity<?> getImageData(@PathVariable(value = "filename") String name){
        return ResponseEntity.ok (resourceLoader.getResource ("file:"+ Paths.get (path,name)));
    }

    //展示图片
    @GetMapping("/showImage")
    String showImage(Model model,@RequestParam String name){
        model.addAttribute ("filename",name);
        return "picView";

    }

    //展示图片
    @GetMapping("/avatar/{username}")
    @ResponseBody
    ResponseEntity<?> getImage(@PathVariable(value = "username") String name){
        User user=userService.selectUserByUsername (name);
        if(user.getAvatar ()==null){
            return null;
        }
        //返回头像路径地址的二进制
         return ResponseEntity.ok (resourceLoader.getResource ("file:"+user.getAvatar ()));

    }
}
