package com.banyuan.springdemo.bean;

import lombok.Data;

import java.util.Date;
@Data
public class Comment {
    private int id;//评论id
    private Date createdTime;//评论创建时间
    private String content;//评论内容
    //private int userId;//用户id
    private User commenter;//评论的用户
    private int blogId;//blog id
}
