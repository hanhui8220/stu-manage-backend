package com.hanhui.stumanage.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Data
@Getter
@Setter
@TableName("stu_info")
public class StudentEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "stu_id", type = IdType.AUTO)
    private Integer stuId;

    /**
     * 学生学号
     */
    @TableField("stu_number")
    private String stuNumber;

    /**
     * 学生姓名
     */
    @TableField("stu_name")
    private String stuName;

    /**
     * 身份证号码
     */
    @TableField("stu_identity_card")
    private String stuIdentityCard;

    /**
     * 性别
     */
    @TableField("stu_gender")
    private String stuGender;

    /**
     * 民族
     */
    @TableField("stu_nation")
    private String stuNation;

    /**
     * 籍贯
     */
    @TableField("stu_native_place")
    private String stuNativePlace;

    /**
     * 学生手机号码
     */
    @TableField("stu_phone_number")
    private String stuPhoneNumber;

    /**
     * 政治面貌
     */
    @TableField("stu_political")
    private String stuPolitical;

    /**
     * 出生日期
     */
    @TableField("stu_birth_date")
    private LocalDate stuBirthDate;

    /**
     * 家庭住址
     */
    @TableField("stu_home_address")
    private String stuHomeAddress;

    /**
     * 紧急联系人
     */
    @TableField("stu_emergency_contact")
    private String stuEmergencyContact;

    /**
     * 紧急联系人号码
     */
    @TableField("stu_emergency_contact_phone")
    private String stuEmergencyContactPhone;

    /**
     * 学生年级
     */
    @TableField("stu_grade")
    private String stuGrade;

    /**
     * 学生院系
     */
    @TableField("stu_faculty")
    private String stuFaculty;

    /**
     * 学生专业
     */
    @TableField("stu_major")
    private String stuMajor;

    /**
     * 学生班级
     */
    @TableField("stu_class")
    private String stuClass;

    /**
     * 备注
     */
    @TableField("remarks")
    private String remarks;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private Date createTime;

}
