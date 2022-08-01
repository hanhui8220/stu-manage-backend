package com.hanhui.stumanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hanhui.stumanage.entity.CourseStuentRelEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hanhui
 * @since 2022-08-01
 */
@Mapper
public interface CourseStudentDao extends BaseMapper<CourseStuentRelEntity> {

}
