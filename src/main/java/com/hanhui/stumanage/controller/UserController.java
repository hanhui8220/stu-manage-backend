package com.hanhui.stumanage.controller;


import com.hanhui.stumanage.model.User;
import com.hanhui.stumanage.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    //用户登录
    @GetMapping("/user/login")
    public User login(String usercode, String password) {
        return userService.findUser(usercode,password);
    }

    //退出登录
    @GetMapping(value = "/user/logout")
    public String logout(HttpSession session) {
        // 清除Session
        session.invalidate();
        return null;
    }


}
