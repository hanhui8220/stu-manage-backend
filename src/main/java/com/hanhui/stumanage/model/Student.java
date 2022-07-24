package com.hanhui.stumanage.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


@Data
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Student implements Serializable {



    private Long stuId;

    /**
     * 学生学号
     */
    private String stuNumber;

    /**
     * 学生姓名
     */
    private String stuName;

    /**
     * 身份证号码
     */
    private String stuIdentityCard;

    /**
     * 性别
     */
    private String stuGender;

    /**
     * 民族
     */
    private String stuNation;

    /**
     * 籍贯
     */
    private String stuNativePlace;

    /**
     * 学生手机号码
     */
    private String stuPhoneNumber;

    /**
     * 政治面貌
     */
    private String stuPolitical;

    /**
     * 出生日期
     */
    private Date stuBirthDate;

    /**
     * 家庭住址
     */
    private String stuHomeAddress;

    /**
     * 紧急联系人
     */
    private String stuEmergencyContact;

    /**
     * 紧急联系人号码
     */
    private String stuEmergencyContactPhone;

    /**
     * 学生年级
     */
    private String stuGrade;

    /**
     * 学生院系
     */
    private String stuFaculty;

    /**
     * 学生专业
     */
    private String stuMajor;

    /**
     * 学生班级
     */
    private String stuClass;

    /**
     * 备注
     */
    private String remarks;

    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;
}
