package com.banyuan.springdemo.dao;

import com.banyuan.springdemo.bean.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    //根据用户姓名查找用户信息方法（测试，无页面渲染）
    User selectUserByUsername(String username);

}
