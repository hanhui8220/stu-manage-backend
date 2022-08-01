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
@TableName("major_info")
public class MajorEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "major_id", type = IdType.AUTO)
    private Integer majorId;

    /**
     * 专业编号
     */
    @TableField("major_number")
    private String majorNumber;

    /**
     * 专业名称
     */
    @TableField("major_name")
    private String majorName;


}
