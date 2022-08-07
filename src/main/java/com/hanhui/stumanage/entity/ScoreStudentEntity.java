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
 * @since 2022-08-03
 */

@Data
@Getter
@Setter
@TableName("score_x_stu")
public class ScoreStudentEntity implements Serializable {

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
     * 课程分数
     */
    @TableField("score")
    private Integer score;

    /**
     * 学期
     */
    @TableField("term")
    private String term;

    /**
     * 学年
     */
    @TableField("year")
    private String year;


}
