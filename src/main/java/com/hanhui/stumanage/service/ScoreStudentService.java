package com.hanhui.stumanage.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanhui.stumanage.dao.CourseStudentDao;
import com.hanhui.stumanage.dao.ScoreStudentDao;
import com.hanhui.stumanage.entity.CourseStuentRelEntity;
import com.hanhui.stumanage.entity.ScoreStudentEntity;
import com.hanhui.stumanage.exception.GenricException;
import com.hanhui.stumanage.mapper.ScoreStudentMapper;
import com.hanhui.stumanage.model.ScoreStudent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScoreStudentService {

    @Resource
    private ScoreStudentDao scoreStudentDao;

    @Resource
    private CourseStudentDao courseStudentDao;

    public ScoreStudent insert(ScoreStudent scoreStudent){
        // 判断 该学生有没有选择该门课
        QueryWrapper queryWrapper = new QueryWrapper<CourseStuentRelEntity>();
        queryWrapper.eq("course_number",scoreStudent.getCourseNumber());
        queryWrapper.eq("stu_number",scoreStudent.getStuNumber());
        CourseStuentRelEntity courseStuentRelEntity = courseStudentDao.selectOne(queryWrapper);
        if(courseStuentRelEntity == null ){
            throw  GenricException.generateStudentCourseNotExistError();
        }
        //  判断 该门课  该学年 和 该学期 有没有录入过
        QueryWrapper wrapper = new QueryWrapper<ScoreStudentEntity>();
        wrapper.eq("course_number",scoreStudent.getCourseNumber());
        wrapper.eq("stu_number",scoreStudent.getStuNumber());
        wrapper.eq("term",scoreStudent.getTerm());
        wrapper.eq("year",scoreStudent.getYear());
        ScoreStudentEntity scoreStudentEntity = scoreStudentDao.selectOne(wrapper);
        if(scoreStudentEntity != null ){
            throw  GenricException.generateScoreAlreadyExistError();
        }
        ScoreStudentEntity scoreStudentEntity1 = ScoreStudentMapper.INSTANCE.fromModel(scoreStudent);
        scoreStudentDao.insert(scoreStudentEntity1);
        scoreStudent.setId(scoreStudentEntity1.getId());
        return scoreStudent;
    }


    public ScoreStudent findById(Integer scoreStuId){
        return ScoreStudentMapper.INSTANCE.fromEntity(scoreStudentDao.selectById(scoreStuId));
    }

    public ScoreStudent updateById(ScoreStudent scoreStudent){
        // 判断 该学生有没有选择该门课
        QueryWrapper queryWrapper = new QueryWrapper<CourseStuentRelEntity>();
        queryWrapper.eq("course_number",scoreStudent.getCourseNumber());
        queryWrapper.eq("stu_number",scoreStudent.getStuNumber());
        CourseStuentRelEntity courseStuentRelEntity = courseStudentDao.selectOne(queryWrapper);
        if(courseStuentRelEntity == null ){
            throw  GenricException.generateStudentCourseNotExistError();
        }
        //  判断 该门课  该学年 和 该学期 有没有录入过
        QueryWrapper wrapper = new QueryWrapper<ScoreStudentEntity>();
        wrapper.eq("course_number",scoreStudent.getCourseNumber());
        wrapper.eq("stu_number",scoreStudent.getStuNumber());
        wrapper.eq("term",scoreStudent.getTerm());
        wrapper.eq("year",scoreStudent.getYear());
        List selectList = scoreStudentDao.selectList(wrapper);
        if(selectList.size() > 1 ){
            throw  GenricException.generateScoreAlreadyExistError();
        }
        ScoreStudentEntity scoreStudentEntity = ScoreStudentMapper.INSTANCE.fromModel(scoreStudent);
        scoreStudentDao.updateById(scoreStudentEntity);
        scoreStudent.setId(scoreStudentEntity.getId());
        return scoreStudent;
    }

    public IPage<ScoreStudent> findList(ScoreStudent scoreStudent, Page page){
        IPage<ScoreStudent> iPage = scoreStudentDao.findByPage(page, scoreStudent);
        return iPage;
    }

    public Integer deleteByIds(List<String> ids) {
        int deleteBatchIds = scoreStudentDao.deleteBatchIds(ids);
        return deleteBatchIds;
    }

    public IPage<ScoreStudent> findSelf(ScoreStudent scoreStudent, Page page) {
        IPage<ScoreStudent> iPage = scoreStudentDao.findSelf(page, scoreStudent);
        return iPage;
    }
}
