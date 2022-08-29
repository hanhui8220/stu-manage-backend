package com.hanhui.stumanage.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanhui.stumanage.cache.CacheEvict;
import com.hanhui.stumanage.cache.RedisCache;
import com.hanhui.stumanage.constant.CacheConst;
import com.hanhui.stumanage.dao.CourseDao;
import com.hanhui.stumanage.entity.CourseEntity;
import com.hanhui.stumanage.exception.GenricException;
import com.hanhui.stumanage.mapper.CourseMapper;
import com.hanhui.stumanage.model.Course;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseService {

    @Resource
    private CourseDao courseDao;


    public Course insert(Course course){
        QueryWrapper<CourseEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_number",course.getCourseNumber());
        CourseEntity courseEntity = courseDao.selectOne(queryWrapper);
        if(courseEntity != null ){
            throw GenricException.generateCourseNumberError();
        }
        CourseEntity entity = CourseMapper.INSTANCE.fromModel(course);
        courseDao.insert(entity);
        course.setCourseId(entity.getCourseId());
        return course;
    }

    @RedisCache(key = "#courseId",name = CacheConst.COURSE)
    public Course findById(Integer courseId){
        return CourseMapper.INSTANCE.fromEntity(courseDao.selectById(courseId));
    }

    @CacheEvict(key = "#course.getCourseId()",name = CacheConst.COURSE)
    public Course updateById(Course course){
        QueryWrapper<CourseEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_number",course.getCourseNumber());
        List<CourseEntity> selectList = courseDao.selectList(queryWrapper);
        if(selectList.size() > 1 ){
            throw GenricException.generateCourseNumberError();
        }
        CourseEntity entity = CourseMapper.INSTANCE.fromModel(course);
        courseDao.updateById(entity);
        course.setCourseId(entity.getCourseId());
        return course;
    }

    public IPage<Course> findList(Course course, Page page) {
        QueryWrapper<CourseEntity> queryWrapper = new QueryWrapper<>();
        if(StringUtils.hasLength(course.getCourseNumber())){
            queryWrapper.like("course_number",course.getCourseNumber());
        }
        if(StringUtils.hasLength(course.getCourseName())){
            queryWrapper.like("course_name",course.getCourseName());
        }

        IPage selectPage = courseDao.selectPage(page, queryWrapper);
        List records = selectPage.getRecords();
        List models = CourseMapper.INSTANCE.fromEntities(records);
        selectPage.setRecords(models);
        return selectPage;
    }

    // 选修课 可选 人数 -1
    public Course decreaseRemain(String courseNumber){
        QueryWrapper<CourseEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("course_number",courseNumber);
        CourseEntity courseEntity = courseDao.selectOne(wrapper);
        if(courseEntity.getCourseType() == 1){
            //   type = 1 : 如果是选修课 判断剩余人数是否够用
            if(courseEntity.getCourseRemain() > 0){
                Integer courseRemain = courseEntity.getCourseRemain();
                courseRemain--;
                courseEntity.setCourseRemain(courseRemain);
                courseDao.updateById(courseEntity);
            }else{
                throw GenricException.generateCourseNotEnoughError();
            }
        }
        return CourseMapper.INSTANCE.fromEntity(courseEntity);
    }


}
