package com.hanhui.stumanage.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hanhui.stumanage.dao.UserDao;
import com.hanhui.stumanage.entity.UserEntity;
import com.hanhui.stumanage.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService  {

    @Resource
    private UserDao userDao;


    public User findUser(String usercode, String passWord) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_code",usercode);
        queryWrapper.eq("user_password",passWord);

        UserEntity entity = userDao.selectOne(queryWrapper);
//        User user = UserMapper.INSTANCE.fromEntity(entity);
        User user = new User();
        BeanUtils.copyProperties(entity,user);
        return user;
    }
}
