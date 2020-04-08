package com.banyuan.springdemo.bean;

import lombok.Data;
import org.springframework.stereotype.Repository;

/*
* User实体类，对应mysql 中user表
* */

//自动生产get set 和构造方法
@Data
public class User {
    private int id; //用户id
    private String name; //用户姓名
    private String password; //用户密码
    private String email; //用户email
    private String avatar;//用户头像
}
