package com.hanhui.stumanage.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;


@Data
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ClassInfo implements Serializable {


    /**
     * 主键ID
     */
    private Integer classId;

    /**
     * 班级编号
     */
    private String classNumber;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 辅导员/班主任编号
     */
    private String classManager;

    private Teacher teacher;

    private List<Student> students;
}
