package com.hanhui.stumanage.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanhui.stumanage.dao.TeacherDao;
import com.hanhui.stumanage.entity.StudentEntity;
import com.hanhui.stumanage.entity.TeacherEntity;
import com.hanhui.stumanage.exception.GenricException;
import com.hanhui.stumanage.mapper.StudentMapper;
import com.hanhui.stumanage.mapper.TeacherMapper;
import com.hanhui.stumanage.model.Student;
import com.hanhui.stumanage.model.Teacher;
import com.hanhui.stumanage.model.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeacherService {

    @Resource
    private TeacherDao teacherDao;

    @Resource
    private UserService userService;

    public Teacher insert(Teacher teacher){
        QueryWrapper<TeacherEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teach_code",teacher.getTeachCode());
        TeacherEntity entity = teacherDao.selectOne(queryWrapper);
        if(entity != null ){
            throw GenricException.generateTeacherCodeError();
        }
        TeacherEntity teacherEntity = TeacherMapper.INSTANCE.fromModel(teacher);
        int i = teacherDao.insert(teacherEntity);
        // insert user
        User user = new User()
                .setUserCode(teacher.getTeachCode())
                .setUserName(teacher.getTeachName())
                .setPassWord("123")
                .setStatus(1);
        userService.inserUser(user);
        teacher.setTeachId(teacherEntity.getTeachId());
        return teacher;
    }

    public Teacher findById(Long stuId) {
        return TeacherMapper.INSTANCE.fromEntity(teacherDao.selectById(stuId));
    }

    public Teacher updateById(Teacher teacher) {
        TeacherEntity entity = TeacherMapper.INSTANCE.fromModel(teacher);

        QueryWrapper<TeacherEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teach_code",teacher.getTeachCode());
        List<TeacherEntity> selectList = teacherDao.selectList(queryWrapper);
        if(selectList != null ){
            if(selectList.size() > 1){
                throw GenricException.generateTeacherCodeError();
            }
        }
        int updateById = teacherDao.updateById(entity);
        return teacher;
    }

    public IPage<Teacher> findList(Teacher teacher, Page page) {
        QueryWrapper<TeacherEntity> queryWrapper = new QueryWrapper<>();
        if(StringUtils.hasLength(teacher.getTeachCode())){
            queryWrapper.like("teach_code",teacher.getTeachCode());
        }
        if(StringUtils.hasLength(teacher.getTeachName())){
            queryWrapper.like("teach_name",teacher.getTeachName());
        }
        if( teacher.getStatus() != null){
            queryWrapper.eq("teach_status",teacher.getStatus());
        }
        IPage selectPage = teacherDao.selectPage(page, queryWrapper);
        List records = selectPage.getRecords();
        List models = TeacherMapper.INSTANCE.fromEntities(records);
        selectPage.setRecords(models);
        return selectPage;
    }
}
