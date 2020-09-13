package com.tq.mybatis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration//申明为配置类

public class MyMvcConfig implements WebMvcConfigurer {//实现WebMvcConfigurer接口

//    @Autowired
//    MyInterceptor nInterceptor;
    //增加视图管理方法
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/mylogin").setViewName("logintest");//发送请求并跳转到相应界面
        registry.addViewController("/logintest.html").setViewName("logintest");
        registry.addViewController("/admin/admin_1.html").setViewName("admin/admin_1");
        registry.addViewController("/admin/admin_2.html").setViewName("admin/admin_2");
        registry.addViewController("/admin/welcome").setViewName("admin/welcome");
        registry.addViewController("/admin/comment").setViewName("admin/comment");
        registry.addViewController("/admin/article").setViewName("admin/article");
        registry.addViewController("/admin/show").setViewName("admin/show");
        registry.addViewController("/admin/user").setViewName("admin/user");
        registry.addViewController("/admin/showpic").setViewName("showpic");
        registry.addViewController("/admin/echerts").setViewName("admin/echerts");
        registry.addViewController("/admin/baidumap").setViewName("admin/baidumap");
        registry.addViewController("/admin/test").setViewName("admin/test");
       // registry.addViewController("/admin/download").setViewName("/admin/download");
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(nInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/admin/admin_2.html");
//    }
}
