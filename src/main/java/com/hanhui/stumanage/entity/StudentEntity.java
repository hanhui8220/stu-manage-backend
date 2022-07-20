package com.hanhui.stumanage.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@TableName("stu_info")
public class StudentEntity {

    @TableId(type = IdType.UUID)
    private String studentId;

    @TableField("student_name")
    private String studentName;

    @TableField("student_grade")
    private String studentGrade;

    @TableField("student_faculty")
    private String studentFaculty;

    @TableField("student_major")
    private String studentMajor;

    @TableField("student_class")
    private String studentClass;

    @TableField("createTime")
    private Date createTime;

}
