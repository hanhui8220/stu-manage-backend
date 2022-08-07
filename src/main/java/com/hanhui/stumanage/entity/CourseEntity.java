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
 * @since 2022-08-01
 */

@Data
@Getter
@Setter
@TableName("course_info")
public class CourseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "course_id", type = IdType.AUTO)
    private Integer courseId;

    /**
     * 课程编号
     */
    @TableField("course_number")
    private String courseNumber;

    /**
     * 课程名称
     */
    @TableField("course_name")
    private String courseName;


    @TableField("course_type")
    private Integer courseType;

    @TableField("course_total")
    private Integer courseTotal;

    @TableField("course_remain")
    private Integer courseRemain;
}
