package com.hanhui.stumanage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanhui.stumanage.dao.CourseStudentDao;
import com.hanhui.stumanage.dao.StudentDao;
import com.hanhui.stumanage.entity.CourseStuentRelEntity;
import com.hanhui.stumanage.entity.StudentEntity;
import com.hanhui.stumanage.exception.GenricException;
import com.hanhui.stumanage.mapper.CourseStudenMapper;
import com.hanhui.stumanage.mapper.StudentMapper;
import com.hanhui.stumanage.model.CourseStudent;
import com.hanhui.stumanage.model.Student;
import com.hanhui.stumanage.model.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentService {

    @Resource
    private StudentDao studentDao;

    @Resource
    private UserService userService;

    @Resource
    private CourseStudentDao courseStudentDao;

    public Student insert(Student student){
        QueryWrapper<StudentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_number",student.getStuNumber());
        StudentEntity entity = studentDao.selectOne(queryWrapper);
        if(entity != null ){
            throw GenricException.generateStuNumberError();
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
                throw GenricException.generateStuNumberError();
            }
        }
        int updateById = studentDao.updateById(entity);
        return student;
    }

    public IPage<Student> findList(Student student, Page page) {
        QueryWrapper<StudentEntity> queryWrapper = new QueryWrapper<>();
        if(StringUtils.hasLength(student.getStuName())){
            queryWrapper.like("stu_name",student.getStuName());
        }
        if(StringUtils.hasLength(student.getStuNumber())){
            queryWrapper.like("stu_number",student.getStuNumber());
        }
        if(student.getStatus() != null){
            queryWrapper.eq("stu_status",student.getStatus());
        }
        IPage selectPage = studentDao.selectPage(page, queryWrapper);
        List records = selectPage.getRecords();
        List models = StudentMapper.INSTANCE.fromEntities(records);
        selectPage.setRecords(models);
        return selectPage;
    }

    public CourseStudent chooseCourse(CourseStudent courseStudent) {
        // 先删后插
        UpdateWrapper updateWrapper = new UpdateWrapper<CourseStuentRelEntity>();
        updateWrapper.eq("course_number",courseStudent.getCourseNumber());
        updateWrapper.eq("stu_number",courseStudent.getStuNumber());
        courseStudentDao.delete(updateWrapper);

        CourseStuentRelEntity courseStuentRelEntity = new CourseStuentRelEntity()
                .setCourseNumber(courseStudent.getCourseNumber()).setStuNumber(courseStudent.getStuNumber());
        courseStudentDao.insert(courseStuentRelEntity);
        courseStudent.setId(courseStuentRelEntity.getId());
        return courseStudent;
    }
}
