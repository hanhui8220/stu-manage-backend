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
 * @since 2022-07-30
 */

@Accessors(chain = true)
@Data
@Getter
@Setter
@TableName("class_x_stu")
public class ClassStudentRelEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 班级编号
     */
    @TableField("class_number")
    private String classNumber;

    /**
     * 学生学号
     */
    @TableField("stu_number")
    private String stuNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    @Override
    public String toString() {
        return "ClassXStu{" +
                "id=" + id +
                ", classNumber=" + classNumber +
                ", stuNumber=" + stuNumber +
                "}";
    }
}
