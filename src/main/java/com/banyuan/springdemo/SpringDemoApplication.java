package com.banyuan.springdemo;

import com.banyuan.springdemo.bean.Blog;
import com.banyuan.springdemo.bean.Comment;
import com.banyuan.springdemo.bean.User;
import com.banyuan.springdemo.dao.BlogDao;
import com.banyuan.springdemo.dao.CommentDao;
import com.banyuan.springdemo.dao.UserDao;
import com.banyuan.springdemo.service.EmailService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
@MapperScan("com.banyuan.springdemo.dao")
public class SpringDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context= SpringApplication.run (SpringDemoApplication.class, args);
        //CommentDao commentDao = context.getBean (CommentDao.class);
        //EmailService email = context.getBean (EmailService.class);
        //email.sendMailActive ("weng");
        //List<Comment> comments = commentDao.selectCommentsByBlogId (30);
        //System.out.println (comments);
        //System.out.println (blog.selectBlogListByUsername ("张三1"));
        //BlogDao blogDao = context.getBean (BlogDao.class);
        //Blog blog=new Blog ();
       // List<Blog> blogs = blogDao.selectBlogListByUsername ("张三97");
        //System.out.println (blogs);
        //blog.setContent ("123213213123");
        //blog.setTitle ("sdasdasdasd");
        //blogDao.insertBlogByCreate (blog);
        //System.out.println (blog.getId ());
        //Blog blog = blogDao.selectBlogDetailByBlogId (88);
        /*[Blog(id=88, title=这是标题87, content=这是博客内容87 87, createdTime=Tue Jan 14 19:29:02 CST 2020, userId=87, comments=[Comment(id=19, createdTime=Fri Jan 17 21:12:48 CST 2020, content=来啊，战个痛苦, userId=99, blogId=88), Comment(id=20, createdTime=Fri Jan 17 21:14:24 CST 2020, content=我是大牛逼, userId=3, blogId=88), Comment(id=21, createdTime=Fri Jan 17 23:25:49 CST 2020, content=牛逼牛逼, userId=79, blogId=88)], users=[User(id=99, name=张三97, password=null, email=null), User(id=3, name=张三1, password=null, email=null), User(id=79, name=张三77, password=null, email=null)])]
        *
        * */
        //System.out.println (blog);
        //List<Blog> blogs = blogDao.selectBlogListByUsername ("张三97");
        //System.out.println (blogs);


        //[Blog(id=4, title=这是标题3, content=这是博客内容3 3, createdTime=Tue Jan 14 19:29:01 CST 2020, userId=3)]
        //UserDao userDao = context.getBean (UserDao.class);
        //User(id=3, name=张三1, password=zhangsan1, email=zhangsan@1)
        // System.out.println (userDao.selectUserByUsername ("张三1"));
    }

}