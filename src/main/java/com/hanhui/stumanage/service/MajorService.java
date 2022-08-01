package com.hanhui.stumanage.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanhui.stumanage.dao.MajorDao;
import com.hanhui.stumanage.entity.CourseEntity;
import com.hanhui.stumanage.entity.MajorEntity;
import com.hanhui.stumanage.exception.GenricException;
import com.hanhui.stumanage.mapper.CourseMapper;
import com.hanhui.stumanage.mapper.MajorMapper;
import com.hanhui.stumanage.model.Course;
import com.hanhui.stumanage.model.Major;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MajorService {

    @Resource
    private MajorDao majorDao;

    public Major insert(Major major){
        QueryWrapper<MajorEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("major_number",major.getMajorNumber());
        MajorEntity majorEntity = majorDao.selectOne(queryWrapper);
        if(majorEntity != null ){
            throw GenricException.generateMajorNumberError();
        }
        MajorEntity entity = MajorMapper.INSTANCE.fromModel(major);
        majorDao.insert(entity);
        major.setMajorId(entity.getMajorId());
        return major;
    }

    public Major findById(Integer majorId){
        return MajorMapper.INSTANCE.fromEntity(majorDao.selectById(majorId));
    }

    public Major updateById(Major major){
        QueryWrapper<MajorEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("major_number",major.getMajorNumber());
        List<MajorEntity> selectList = majorDao.selectList(queryWrapper);
        if(selectList.size() > 1 ){
            throw GenricException.generateMajorNumberError();
        }
        MajorEntity entity = MajorMapper.INSTANCE.fromModel(major);
        majorDao.updateById(entity);
        major.setMajorId(entity.getMajorId());
        return major;
    }

    public IPage<Major> findList(Major major, Page page) {
        QueryWrapper<MajorEntity> queryWrapper = new QueryWrapper<>();
        if(StringUtils.hasLength(major.getMajorNumber())){
            queryWrapper.like("major_number",major.getMajorNumber());
        }
        if(StringUtils.hasLength(major.getMajorName())){
            queryWrapper.like("major_name",major.getMajorName());
        }

        IPage selectPage = majorDao.selectPage(page, queryWrapper);
        List records = selectPage.getRecords();
        List models = MajorMapper.INSTANCE.fromEntities(records);
        selectPage.setRecords(models);
        return selectPage;
    }
}
