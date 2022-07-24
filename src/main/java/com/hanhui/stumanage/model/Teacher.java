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

@Data
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Teacher implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * 教师id
         */
        private Long teachId;

        /**
         * 教师编号
         */
        private String teachCode;

        /**
         * 教师名称
         */
        private String teachName;

        /**
         * 身份证号码
         */
        private String teachIdentityCard;

        /**
         * 性别
         */
        private String teachGender;

        /**
         * 教师手机号码
         */
        private String teachPhoneNumber;

        /**
         * 1:正常,0:暂停
         */
        private Integer status;

}
