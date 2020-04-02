package com.banyuan.springdemo.interceptor;

import com.banyuan.springdemo.bean.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class loginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1、获取user对象
        User user = (User)request.getSession ().getAttribute ("USER");
        //2、判断user对象是否为空
        if(user!=null){
            //2、1如果不为空，则表示用于已经登陆过，放行
            return true;
        }else {
            //2、2如果为空，则表示用于未登陆，跳转到login界面，并带上当前页面的uri
            response.sendRedirect ("/login?next="+request.getRequestURI ());
            return false;
        }



    }
}
