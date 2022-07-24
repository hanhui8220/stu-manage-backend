package com.hanhui.stumanage.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanhui.stumanage.model.User;
import com.hanhui.stumanage.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {

    @Resource
    private UserService userService;

    //用户登录
    @GetMapping("/login")
    public User login(String usercode, String password) {
        return userService.findUser(usercode,password);
    }

    //退出登录
    @GetMapping(value = "/logout")
    public String logout(HttpSession session) {
        // 清除Session
        session.invalidate();
        return null;
    }


    /**
     *  注册新用户
     * @param user
     * @return
     */
    @PostMapping()
    public ResponseEntity<User> register(@RequestBody User user){
        return ResponseEntity.ok(userService.inserUser(user));
    }


    @GetMapping()
    public ResponseEntity<IPage<User>> findList(User user, Page page){
        return ResponseEntity.ok(userService.findList(user,page));
    }


    @DeleteMapping()
    public ResponseEntity<Integer> deleteByIds(@RequestBody List<String> userIds){
        return ResponseEntity.ok(userService.deleteByIds(userIds));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> findById( @PathVariable("userId") Long userId){
        return ResponseEntity.ok(userService.findById(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateById( @PathVariable("userId") Long userId,@RequestBody User user){
        user.setUserId(userId);
        return ResponseEntity.ok(userService.updateById(user));
    }
}
