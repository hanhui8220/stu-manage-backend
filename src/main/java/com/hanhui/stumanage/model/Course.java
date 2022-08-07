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
public class Course implements Serializable {

    /**
     * 主键ID
     */
    private Integer courseId;

    /**
     * 课程编号
     */
    private String courseNumber;

    /**
     * 课程名称
     */
    private String courseName;

    private Integer courseType;

    private Integer courseTotal;

    private Integer courseRemain;
}
