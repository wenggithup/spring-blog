package com.banyuan.springdemo.bean;

/*
* blog实体类，对应mysql blog表
* */

import lombok.Data;

import java.util.Date;

@Data
public class Blog {
  private int id;
  private String title;
  private String content;
  private Date createdTime;
  private int userId;
}
