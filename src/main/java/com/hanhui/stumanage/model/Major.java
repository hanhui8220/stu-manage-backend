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
public class Major implements Serializable {


    /**
     * 主键ID
     */
    private Integer majorId;

    /**
     * 专业编号
     */
    private String majorNumber;

    /**
     * 专业名称
     */
    private String majorName;
}
