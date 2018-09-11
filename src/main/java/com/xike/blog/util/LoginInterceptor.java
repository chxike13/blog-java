package com.xike.blog.util;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("------------------拦截----------------------");
        if (request.getSession().getAttribute("userName") != null){
            return true;
        }else{
            System.out.println("开始重定向");
            response.sendRedirect("login");
            return true;
        }
    }
}
