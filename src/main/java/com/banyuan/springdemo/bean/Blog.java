package com.banyuan.springdemo.bean;

/*
* blog实体类，对应mysql blog表
* */

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Blog {
  private int id;//博客id
  private String title;//博客标题
  private String content;//博客内容
  private Date createdTime;//博客创建时间
  //private int userId;//用户id
  private User author;//用户对象
  private List<Comment> comments;//评论对象

}
