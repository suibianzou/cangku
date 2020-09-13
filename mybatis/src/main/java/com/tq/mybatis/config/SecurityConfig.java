package com.tq.mybatis.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@EnableWebSecurity//启用Web的安全注解
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //密码需要设置编码器
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        //使用内存用户信息做测试
        auth.inMemoryAuthentication().passwordEncoder(encoder)
                .withUser("小李").password(encoder.encode("123456")).roles("common")
                .and()
                .withUser("小人").password(encoder.encode("111111")).roles("vip");
//        String userSQL="select * from user where name=?";
//        auth.jdbcAuthentication().passwordEncoder(encoder).dataSource(dataSource)
//                .usersByUsernameQuery(userSQL);
    }

    //http协议的请求
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/imgs/**").permitAll()
                .antMatchers("/admin/**").hasRole("vip")
                .antMatchers("/admin/**").hasRole("common")
                .anyRequest().authenticated();
               // .and().formLogin();



        http.headers().frameOptions().disable();
        http.csrf().disable();

        http.formLogin()
                .loginPage("/login").permitAll()
                //.loginProcessingUrl("/admin/admin_1")
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/admin/admin_1.html")
                .failureUrl("/login?error")
                .and().csrf().disable();
    }
}
