//package com.hanhui.stumanage.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//
//@Configuration
//public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests() // 开启 HttpSecurity 配置
//                .antMatchers("/admin/**").permitAll() // admin/** 模式URL必须具备ADMIN角色
//                .antMatchers("/user/**").permitAll() // 该模式需要ADMIN或USER角色
//                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')") // 需ADMIN和DBA角色
//                .anyRequest().authenticated() // 用户访问其它URL都必须认证后访问（登录后访问）
//                .and().formLogin().loginProcessingUrl("/login").permitAll() // 开启表单登录并配置登录接口
//                .and().csrf().disable(); // 关闭csrf
//    }
//}
