package com.banyuan.springdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class searchController {

    //通过参数查找对应的值并返回
    @ResponseBody
    @GetMapping("/search")
    String getResultBySearch(@RequestParam(value = "key") String keyword){
        return "The key is"+keyword;
    }
}
