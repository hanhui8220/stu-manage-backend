package com.hanhui.stumanage.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@TableName("sys_user")
public class UserEntity {

/**
 * 主键
 * @TableId中可以决定主键的类型,不写会采取默认值,默认值可以在yml中配置
 * AUTO: 数据库ID自增
 * INPUT: 用户输入ID
 * ID_WORKER: 全局唯一ID，Long类型的主键
 * ID_WORKER_STR: 字符串全局唯一ID
 * UUID: 全局唯一ID，UUID类型的主键
 * NONE: 该类型为未设置主键类型
 */
    @TableId(type = IdType.AUTO)
    private Long userId;

    @TableField("user_code")
    private String userCode;

    @TableField("user_name")
    private String userName;

    @TableField("user_password")
    private String passWord;

    @TableField("user_status")
    private Integer status;


}
