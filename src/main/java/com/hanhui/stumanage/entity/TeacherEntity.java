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
 * @since 2022-07-24
 */
@Data
@Getter
@Setter
@TableName("teacher_info")
public class TeacherEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 教师id
     */
    @TableId(value = "teach_id", type = IdType.AUTO)
    private Long teachId;

    /**
     * 教师编号
     */
    @TableField("teach_code")
    private String teachCode;

    /**
     * 教师名称
     */
    @TableField("teach_name")
    private String teachName;

    /**
     * 身份证号码
     */
    @TableField("teach_identity_card")
    private String teachIdentityCard;

    /**
     * 性别
     */
    @TableField("teach_gender")
    private String teachGender;

    /**
     * 教师手机号码
     */
    @TableField("teach_phone_number")
    private String teachPhoneNumber;

    /**
     * 1:正常,0:暂停
     */
    @TableField("teach_status")
    private Integer status;


}
