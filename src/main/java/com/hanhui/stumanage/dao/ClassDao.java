package com.hanhui.stumanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanhui.stumanage.entity.ClassEntity;
import com.hanhui.stumanage.model.ClassInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hanhui
 * @since 2022-07-30
 */

@Mapper
public interface ClassDao extends BaseMapper<ClassEntity> {


    IPage<ClassInfo>  findClassByPage(Page page,@Param("classInfo") ClassInfo classInfo);
}
