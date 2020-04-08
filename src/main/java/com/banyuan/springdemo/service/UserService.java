package com.banyuan.springdemo.service;

import com.banyuan.springdemo.bean.User;
import com.banyuan.springdemo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public User selectUserByUsername (String username){
        return userDao.selectUserByUsername (username);
    }

    public void setUserByAvatar(Integer id, String avatar) {
         userDao.updateUser(id,avatar);
    }
}
