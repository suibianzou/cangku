package com.tq.mybatis.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

@Component //定义为组件
public class MyInterceptor implements HandlerInterceptor{

    @Override //拦截器处理器  返回true才会拦截 返回false则不会通过请求控制
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("你已进入拦截器处理！");
        if(request.getSession().getAttribute("loginuser")==null){
            response.sendRedirect("/logintest");

            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        request.setAttribute("year", Calendar.getInstance().get(Calendar.YEAR));
        System.out.println("拦截成功");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
