package com.hanhui.stumanage.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class CourseStudent implements Serializable {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 课程编号
     */
    private String courseNumber;

    /**
     * 学生学号
     */
    private String stuNumber;

    /**
     * 分数
     */
    private Integer score;

}
