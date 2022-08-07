package com.hanhui.stumanage.constant;


import lombok.Getter;

@Getter
public enum  ErrorCodeEnum {

    USER_CODE_EXIST("T_USER_CODE_EXIST","用户账号已存在!"),
    USER_INVALID("T_USER_INVALID","用户名或密码不正确!"),
    STU_NUMBER_EXIST("T_STU_NUMBER_EXIST","学生学号已存在!"),
    TEACHER_CODE_EXIST("T_TEACH_CODE_EXIST","教师编号已存在!"),
    CLASS_NUMBER_EXIST("T_CLASS_NUMBER_EXIST","班级编号已存在!"),
    COURSE_NUMBER_EXIST("T_COURSE_NUMBER_EXIST","课程编号已存在!"),
    MAJOR_NUMBER_EXIST("T_MAJOR_NUMBER_EXIST","专业编号已存在!"),
    STU_COURSE_EXIST("T_STU_COURSE_EXIST","你已选过该课程,无需重复选择!"),
    COURSE_NOT_ENOUGH("T_COURSE_NOT__ENOUGH","该课程选择人数已满,请选择其他课程!"),
    STU_COURSE_NOT_EXIST("T_STU_COURSE_NOT_EXIST","该学生未选择该门课程,无法录入成绩!"),
    SCORE_ALREADY_EXIST("T_SCORE_ALREADY_EXIST","该学生这学期该课程已经录入成绩!");

    private String code;

    private String text;

    private ErrorCodeEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }
}
