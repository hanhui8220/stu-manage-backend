package com.hanhui.stumanage.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanhui.stumanage.cache.CacheEvict;
import com.hanhui.stumanage.cache.RedisCache;
import com.hanhui.stumanage.constant.CacheConst;
import com.hanhui.stumanage.dao.ClassDao;
import com.hanhui.stumanage.dao.ClassStudentDao;
import com.hanhui.stumanage.dao.StudentDao;
import com.hanhui.stumanage.entity.ClassEntity;
import com.hanhui.stumanage.entity.ClassStudentRelEntity;
import com.hanhui.stumanage.entity.StudentEntity;
import com.hanhui.stumanage.exception.GenricException;
import com.hanhui.stumanage.mapper.ClassInfoMapper;
import com.hanhui.stumanage.mapper.StudentMapper;
import com.hanhui.stumanage.model.ClassInfo;
import com.hanhui.stumanage.model.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClassService {

    @Resource
    private ClassDao classDao;

    @Resource
    private StudentDao studentDao;

    @Resource
    private ClassStudentDao classStudentDao;

    public ClassInfo insert(ClassInfo classInfo){
        QueryWrapper<ClassEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("class_number",classInfo.getClassNumber());
        ClassEntity classEntity = classDao.selectOne(queryWrapper);
        if(classEntity != null ){
            throw GenricException.generateClassNumberError();
        }
        ClassEntity saveEntity = ClassInfoMapper.INSTANCE.fromModel(classInfo);
        classDao.insert(saveEntity);
        classInfo.setClassId(saveEntity.getClassId());
        return classInfo;
    }

    @RedisCache(key = "#classId",name = CacheConst.CLASS)
    public ClassInfo findById(Long classId){
        return ClassInfoMapper.INSTANCE.fromEntity(classDao.selectById(classId));
    }


    @CacheEvict(key = "#classInfo.getClassId()",name = CacheConst.CLASS)
    public ClassInfo updateById(ClassInfo classInfo){
        QueryWrapper<ClassEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("class_number",classInfo.getClassNumber());
        List<ClassEntity> selectList = classDao.selectList(queryWrapper);
        if(selectList.size() > 1 ){
            throw GenricException.generateClassNumberError();
        }
        ClassEntity saveEntity = ClassInfoMapper.INSTANCE.fromModel(classInfo);
        classDao.updateById(saveEntity);
        classInfo.setClassId(saveEntity.getClassId());
        return classInfo;
    }


    public IPage<ClassInfo> findList(ClassInfo classInfo, Page page) {
//        QueryWrapper<ClassEntity> queryWrapper = new QueryWrapper<>();
//        if(StringUtils.hasLength(classInfo.getClassName())){
//            queryWrapper.like("class_name",classInfo.getClassName());
//        }
//        if(StringUtils.hasLength(classInfo.getClassNumber())){
//            queryWrapper.like("class_number",classInfo.getClassNumber());
//        }

//        IPage selectPage = classDao.selectPage(page, queryWrapper);
//        List records = selectPage.getRecords();
//        List models = ClassInfoMapper.INSTANCE.fromEntities(records);
//        selectPage.setRecords(models);
        IPage<ClassInfo> infoIPage = classDao.findClassByPage(page,classInfo);

        return infoIPage;
    }


    public Map<String,Object> findStudenByClassNumber(String classNumber) {
        // 查询在当前班级下的学生
        List<Student> assignedStudens = studentDao.findStudendByClassNumber(classNumber);
        // 查询所有学生
        List<StudentEntity> allStudents = studentDao.selectList(new QueryWrapper<StudentEntity>());

        List<String> assignedStuNumbers = assignedStudens.stream().map(Student::getStuNumber).collect(Collectors.toList());
        List<StudentEntity> unAssignedStudetEntities = allStudents.stream().filter(entity -> !assignedStuNumbers.contains(entity.getStuNumber()))
                .collect(Collectors.toList());
        Map<String,Object> result = new HashMap<>();
        result.put("assignedStudents",assignedStudens);
        List<Student> list = StudentMapper.INSTANCE.fromEntities(unAssignedStudetEntities);
        result.put("allStudens",allStudents);
        return result;
    }


    public ClassInfo assignStudent(ClassInfo classInfo) {
        List<String> studentNumbers = classInfo.getStudents().stream().map(Student::getStuNumber).collect(Collectors.toList());
        String classNumber = classInfo.getClassNumber();

        // 先删后插
        UpdateWrapper updateWrapper = new UpdateWrapper<ClassStudentRelEntity>();
        updateWrapper.eq("class_number",classNumber);
        classStudentDao.delete(updateWrapper);

        for (String studentNumber : studentNumbers) {
            ClassStudentRelEntity entity = new ClassStudentRelEntity();
            entity.setClassNumber(classNumber);
            entity.setStuNumber(studentNumber);
            classStudentDao.insert(entity);
        }
       return classInfo;
    }
}
