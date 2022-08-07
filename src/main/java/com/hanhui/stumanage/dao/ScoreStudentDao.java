package com.hanhui.stumanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanhui.stumanage.entity.ScoreStudentEntity;
import com.hanhui.stumanage.model.ScoreStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hanhui
 * @since 2022-08-03
 */
@Mapper
public interface ScoreStudentDao extends BaseMapper<ScoreStudentEntity> {

    IPage<ScoreStudent> findByPage(Page page , @Param("params") ScoreStudent params);

    IPage<ScoreStudent> findSelf(Page page , @Param("params") ScoreStudent params);
}
