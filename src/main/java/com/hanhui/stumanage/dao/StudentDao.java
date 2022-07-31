package com.hanhui.stumanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hanhui.stumanage.entity.StudentEntity;
import com.hanhui.stumanage.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentDao extends BaseMapper<StudentEntity> {


    List<Student>   findStudendByClassNumber(@Param("classNumber") String classNumber);
}
