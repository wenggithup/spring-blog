package com.banyuan.springdemo.service;

import com.banyuan.springdemo.bean.User;
import com.banyuan.springdemo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Qualifier("db")
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=userDao.selectUserByUsername (s);
        if(user!=null){
            return new UserDetailsImpl (user);
        }else throw new UsernameNotFoundException ("user not find");

    }
}
