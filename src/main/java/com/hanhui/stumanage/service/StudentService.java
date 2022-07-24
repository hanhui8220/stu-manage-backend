package com.hanhui.stumanage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanhui.stumanage.dao.StudentDao;
import com.hanhui.stumanage.entity.StudentEntity;
import com.hanhui.stumanage.exception.GenricException;
import com.hanhui.stumanage.mapper.StudentMapper;
import com.hanhui.stumanage.model.Student;
import com.hanhui.stumanage.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentService {

    @Resource
    private StudentDao studentDao;

    @Resource
    private UserService userService;

    public Student insert(Student student){
        QueryWrapper<StudentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_number",student.getStuNumber());
        StudentEntity entity = studentDao.selectOne(queryWrapper);
        if(entity != null ){
            throw GenricException.generateStuNumberErrot();
        }
        StudentEntity studentEntity = StudentMapper.INSTANCE.fromModel(student);
        int i = studentDao.insert(studentEntity);
        // insert user
        User user = new User()
                .setUserCode(student.getStuNumber())
                .setUserName(student.getStuName())
                .setPassWord("123")
                .setStatus(1);
        userService.inserUser(user);
        student.setStuId(studentEntity.getStuId());
        return student;
    }

    public Student findById(Long stuId) {
        return StudentMapper.INSTANCE.fromEntity(studentDao.selectById(stuId));
    }


    public Student updateById(Student student) {
        StudentEntity entity = StudentMapper.INSTANCE.fromModel(student);

        QueryWrapper<StudentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_number",student.getStuNumber());
        List<StudentEntity> selectList = studentDao.selectList(queryWrapper);
        if(selectList != null ){
            if(selectList.size() > 1){
                throw GenricException.generateStuNumberErrot();
            }
        }
        int updateById = studentDao.updateById(entity);
        return student;
    }

    public IPage<Student> findList(Student student, Page page) {
        QueryWrapper<StudentEntity> queryWrapper = new QueryWrapper<>();
        IPage selectPage = studentDao.selectPage(page, queryWrapper);
        List records = selectPage.getRecords();
        List models = StudentMapper.INSTANCE.fromEntities(records);
        selectPage.setRecords(models);
        return selectPage;
    }
}
