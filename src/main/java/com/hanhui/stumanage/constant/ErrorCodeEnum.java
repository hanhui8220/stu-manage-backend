package com.hanhui.stumanage.constant;


import lombok.Getter;

@Getter
public enum  ErrorCodeEnum {

    USER_CODE_EXIST("T_USER_CODE_EXIST","用户账号已存在"),
    USER_INVALID("T_USER_INVALID","用户名或密码不正确"),
    STU_NUMBER_EXIST("T_STU_NUMBER_EXIST","学生学号已存在"),
    TEACHER_CODE_EXIST("T_TEACH_CODE_EXIST","教师编号已存在"),
    CLASS_NUMBER_EXIST("T_CLASS_NUMBER_EXIST","班级编号已存在"),
    COURSE_NUMBER_EXIST("T_COURSE_NUMBER_EXIST","课程编号已存在"),
    MAJOR_NUMBER_EXIST("T_MAJOR_NUMBER_EXIST","专业编号已存在");

    private String code;

    private String text;

    private ErrorCodeEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }
}
