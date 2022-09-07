package com.hanhui.stumanage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanhui.stumanage.cache.CacheEvict;
import com.hanhui.stumanage.cache.RedisCache;
import com.hanhui.stumanage.constant.CacheConst;
import com.hanhui.stumanage.constant.UserRole;
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

    @Resource
    private CourseService courseService;

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
                .setUserRole(UserRole.STUDENT)
                .setStatus(1);
        userService.inserUser(user);
        student.setStuId(studentEntity.getStuId());
        return student;
    }

    @RedisCache(key = "#stuId",name = CacheConst.STUDENT)
    public Student findById(Long stuId) {
        return StudentMapper.INSTANCE.fromEntity(studentDao.selectById(stuId));
    }

    @CacheEvict(key = "#student.getStuId()",name = CacheConst.STUDENT)
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
        QueryWrapper wrapper = new QueryWrapper<CourseStuentRelEntity>();
        wrapper.eq("course_number",courseStudent.getCourseNumber());
        wrapper.eq("stu_number",courseStudent.getStuNumber());
        CourseStuentRelEntity selectOne = courseStudentDao.selectOne(wrapper);
        if(selectOne != null ){
            throw GenricException.generateChooseCourseError();
        }
        // 判断课程剩余是否可用
        courseService.decreaseRemain(courseStudent.getCourseNumber());

        CourseStuentRelEntity courseStuentRelEntity = new CourseStuentRelEntity()
                .setCourseNumber(courseStudent.getCourseNumber()).setStuNumber(courseStudent.getStuNumber());
        courseStudentDao.insert(courseStuentRelEntity);
        courseStudent.setId(courseStuentRelEntity.getId());
        return courseStudent;
    }

    public List<Student> suggest(Student student) {
        return studentDao.findByStuNumberOrStuName(student);
    }

    public List<Student> filterStudent(Student student) {
        QueryWrapper wrapper = new QueryWrapper<StudentEntity>();
        if(student.getStuNumber() != null ){
            wrapper.eq("stu_number",student.getStuNumber());
        }
        List list = studentDao.selectList(wrapper);
        List models = StudentMapper.INSTANCE.fromEntities(list);
        return models;
    }
}
