package com.banyuan.springdemo;

import com.banyuan.springdemo.bean.Blog;
import com.banyuan.springdemo.bean.User;
import com.banyuan.springdemo.dao.BlogDao;
import com.banyuan.springdemo.dao.UserDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.banyuan.springdemo.dao")
public class SpringDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context= SpringApplication.run (SpringDemoApplication.class, args);
        BlogDao blog = context.getBean (BlogDao.class);
        System.out.println (blog.selectBlogListByUsername ("张三1"));

        //[Blog(id=4, title=这是标题3, content=这是博客内容3 3, createdTime=Tue Jan 14 19:29:01 CST 2020, userId=3)]
        //UserDao userDao = context.getBean (UserDao.class);
        //User(id=3, name=张三1, password=zhangsan1, email=zhangsan@1)
        // System.out.println (userDao.selectUserByUsername ("张三1"));
    }

}