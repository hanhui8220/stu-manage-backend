package com.hanhui.stumanage.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanhui.stumanage.constant.UserStatus;
import com.hanhui.stumanage.dao.UserDao;
import com.hanhui.stumanage.entity.UserEntity;
import com.hanhui.stumanage.exception.GenricException;
import com.hanhui.stumanage.mapper.UserMapper;
import com.hanhui.stumanage.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService  {

    @Resource
    private UserDao userDao;


    public User findUser(String usercode, String passWord) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_code",usercode);
        queryWrapper.eq("user_password",passWord);
        queryWrapper.eq("user_status", UserStatus.ACTIVE);
        UserEntity entity = userDao.selectOne(queryWrapper);
        if(entity == null ){
            throw GenricException.generateUserNameOrPwdError();
        }
        User user = UserMapper.INSTANCE.fromEntity(entity);
        return user;
    }


    /**
     *      create user
     * @param user
     * @return
     */
    public User inserUser(User user){
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_code",user.getUserCode());
        UserEntity selectOne = userDao.selectOne(queryWrapper);
        if(selectOne != null ){
            // 用户已存在
            throw GenricException.generateUserCodeExitsError();
        }
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(user,entity);
        int i = userDao.insert(entity);
        user.setUserId(entity.getUserId());
        return user;
    }

    public IPage<User> findList(User user, Page page) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        if(StringUtils.hasLength(user.getUserCode())){
            queryWrapper.like("user_code",user.getUserCode());
        }
        if(StringUtils.hasLength(user.getUserName())){
            queryWrapper.like("user_name",user.getUserName());
        }
        if(user.getStatus() != null){
            queryWrapper.eq("user_status",user.getStatus());
        }
        IPage selectPage = userDao.selectPage(page, queryWrapper);
        List records = selectPage.getRecords();
        List models = UserMapper.INSTANCE.fromEntities(records);
        selectPage.setRecords(models);
        return selectPage;
    }

    public Integer deleteByIds(List<String> userIds) {
        int deleteBatchIds = userDao.deleteBatchIds(userIds);
        return deleteBatchIds;
    }

    public User findById(Long userId) {
        return UserMapper.INSTANCE.fromEntity(userDao.selectById(userId));
    }

    public User updateById(User user) {
        UserEntity entity = UserMapper.INSTANCE.fromModel(user);
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_code",user.getUserCode());
        List<UserEntity> selectList = userDao.selectList(queryWrapper);
        if(selectList.size() > 1 ){
            // 用户已存在
            throw GenricException.generateUserCodeExitsError();
        }
        int updateById = userDao.updateById(entity);
        return user;
    }
}
