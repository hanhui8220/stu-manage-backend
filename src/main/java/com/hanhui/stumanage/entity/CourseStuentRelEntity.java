package com.hanhui.stumanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author hanhui
 * @since 2022-08-01
 */
@Accessors(chain = true)
@Data
@Getter
@Setter
@TableName("course_x_stu")
public class CourseStuentRelEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 课程编号
     */
    @TableField("course_number")
    private String courseNumber;

    /**
     * 学生学号
     */
    @TableField("stu_number")
    private String stuNumber;

    /**
     * 分数
     */
    @TableField("score")
    private Integer score;


}
