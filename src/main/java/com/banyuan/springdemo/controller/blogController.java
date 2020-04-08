package com.banyuan.springdemo.controller;

import com.banyuan.springdemo.bean.Blog;
import com.banyuan.springdemo.bean.Comment;
import com.banyuan.springdemo.bean.User;
import com.banyuan.springdemo.service.BlogService;
import com.banyuan.springdemo.service.CommentService;
import com.banyuan.springdemo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Controller
public class blogController {
    //依赖blogService
    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    //通过blogid展示用户博客,路径变量blogid
    @GetMapping("blog/{blogId}")
    String getBlogByBlogId(@PathVariable(value = "blogId") Integer id, Model model){
        //调用service层findBlogByBlogId获取blog对象
        Blog blog =blogService.findBlogDetailByBlogId (id);
        //调用commentService层方法findCommentByBlogId 获取id对应的comments对象
        List<Comment> comments = commentService.findCommentsByBlogId (id);

        //将blog对象存入域中，方便前台提取数据
        model.addAttribute ("blog",blog);
        //将comments对象存入域中
        model.addAttribute ("comments",comments);
        //返回item.html页面
        return "item";
    }

    //通过blogid展示用户博客，路径变量（blogid）
    //@GetMapping("/blog/{blogId}")
    String getBlogDetailByBlogId(@PathVariable(value = "blogId") Integer id,Model model){
        //调用service层findBlogByBlogId获取blog对象，含用户对象，含cooment对象
        //List<Blog> blogs=blogService.findBlogDetailByBlogId (id);
        //将blog存在域中(前端代码评论部分，以及user.name），用blog来获取

       // model.addAttribute ("blog",blogs);
        //返回页面
        return "";
    }

    //通过用户姓名查询用户博客列表
    @GetMapping("blogger/{username}")
    String findBlogByUsername(@PathVariable(value = "username") String username,
                              @RequestParam Optional<Integer> page,
                              @RequestParam Optional<Integer> size,
                              Model model){
        //使用分页插件 pageHelper
        PageHelper.startPage (page.orElse (1),size.orElse (10),"id asc");
        //调用serivce层方法，获得blog list集合
        List<Blog> blogs=blogService.findBlogListByUsername(username);
        //将blogs放入pageInfo中
        //System.out.println (blogs);
        PageInfo<Blog> pageInfo=new PageInfo<Blog> (blogs);
        //System.out.println ("===================");
        //System.out.println (pageInfo);;

        //将pageinfo对象和user对象放入域中
        model.addAttribute ("blogs",pageInfo);
        model.addAttribute ("user",blogs.get(0).getAuthor ());
        return "list";
    }
    //展示blog界面
    @GetMapping("blog/create")
    String showBlogCreate(HttpSession session, HttpServletRequest request){
        /*//1、获取session域中的user用户
        User user =(User) session.getAttribute ("USER");
        //2、判断用户是否存在，如果存在，则用户已经登陆，否则用户未登陆
        if(user!=null){
            //2、1存在用户，则返回create界面
            return "create";
        }else {
            //2、2用户不存在，将uri添加到路径中，并重定向到login界面
            //3、获取当前uri
            String next = request.getRequestURI ();
            //4、将uri作为参数放入路径，并到登陆界面
            return "redirect:/login?next=".concat(next);*/
        return "create";



    }

    //获取blog create页面表单元素内容,表单元素title content
    @PostMapping("blog/create")
    String insertBlogByCreate(@RequestParam(value = "title") String title,
                              @RequestParam(value = "content") String content,
                              Principal principal){
        //插入blog，并重定向到刚插入的博客
        Blog blog=new Blog ();
        //通过姓名返回user对象
        String name = principal.getName ();
        User user =userService.selectUserByUsername (name);
        blog.setAuthor (user);
        blog.setTitle (title);
        blog.setContent (content);
        blogService.insertBlogByCreate(blog);
        return "redirect:/blog/"+blog.getId ();
    }
    //删除博客
    @DeleteMapping("blog/{blogId}")
    String deleteBlogByBlogId(@PathVariable(value = "blogId") Integer blogId,
                              Principal principal ){
        //取出session中User
        String name = principal.getName ();
        User user =userService.selectUserByUsername (name);
        Blog blog = blogService.findBlogDetailByBlogId (blogId);
        //，判断当前登陆用户和操作用户是否是同一个人
        if(user.getName ().equals (blog.getAuthor ().getName ())){
            //1、1如果是同一个人，执行delete操作
            blogService.deleteBlogByBlogId(blogId);
        }

        return "redirect:/admin/blogs";
    }

    //编辑博客
    @GetMapping("blog/{blogId}/edit")
    String showEdit(@PathVariable(value = "blogId") Integer id,Model model){
        //调用方法，获取blog对象
        Blog blog = blogService.findBlogByBlogId (id);
        //将blog对象放入域中
        model.addAttribute ("blog",blog);
        return "edit";
    }

    @PutMapping("blog/{blogId}/edit")
    String updateBlog(@PathVariable(value = "blogId") Integer id,
                      @RequestParam String title,
                      @RequestParam String content){
        //调用方法更新博客信息
        blogService.updateBlog(title,content,id);

        return "redirect:/blog/"+id;
    }

}
