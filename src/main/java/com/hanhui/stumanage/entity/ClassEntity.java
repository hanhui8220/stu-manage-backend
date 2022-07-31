package com.hanhui.stumanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author hanhui
 * @since 2022-07-30
 */
@Data
@Getter
@Setter
@TableName("class_info")
public class ClassEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "class_id", type = IdType.AUTO)
    private Integer classId;

    /**
     * 班级编号
     */
    @TableField("class_number")
    private String classNumber;

    /**
     * 班级名称
     */
    @TableField("class_name")
    private String className;

    /**
     * 辅导员/班主任编号
     */
    @TableField("class_manager")
    private String classManager;


}
