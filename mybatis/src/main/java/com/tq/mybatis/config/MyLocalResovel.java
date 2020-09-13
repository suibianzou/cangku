package com.tq.mybatis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Configuration//定义该类是配置类

public class MyLocalResovel implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest){
        String lang=httpServletRequest.getParameter("lang");
        String headLang=httpServletRequest.getHeader("Accept-Language");
        Locale locale=null;
        if(!StringUtils.isEmpty(lang)){
            String [] split=lang.split("_");
            locale = new Locale(split[0],split[1]);
        }else{
            String [] split=headLang.split("-");
            locale = new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocalResovel();
    }
}
