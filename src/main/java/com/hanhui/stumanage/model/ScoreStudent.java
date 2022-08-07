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
public class ScoreStudent implements Serializable {

    private Integer id;

    /**
     * 课程编号
     */
    private String courseNumber;

    private String courseName;

    /**
     * 学生学号
     */
    private String stuNumber;

    private String stuName;

    /**
     * 课程分数
     */
    private Integer score;

    /**
     * 学期
     */
    private String term;

    /**
     * 学年
     */
    private String year;

    private String stuFaculty;

    private String stuMajor;
}
