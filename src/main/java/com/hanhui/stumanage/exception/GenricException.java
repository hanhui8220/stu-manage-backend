package com.hanhui.stumanage.exception;

import com.hanhui.stumanage.constant.ErrorCodeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenricException extends RuntimeException {

    private String code;

    public GenricException(String message) {
        super(message);
    }

    public GenricException(String code, String message) {
        super(message);
        this.code = code;
    }

    public static GenricException generateUserCodeExitsError(){
        return new GenricException(ErrorCodeEnum.USER_CODE_EXIST.getCode()
                ,ErrorCodeEnum.USER_CODE_EXIST.getText());
    }

    public static GenricException generateUserNameOrPwdError(){
        return new GenricException(ErrorCodeEnum.USER_INVALID.getCode()
                ,ErrorCodeEnum.USER_INVALID.getText());
    }

    public static GenricException generateStuNumberError(){
        return new GenricException(ErrorCodeEnum.STU_NUMBER_EXIST.getCode()
                ,ErrorCodeEnum.STU_NUMBER_EXIST.getText());
    }

    public static GenricException generateTeacherCodeError(){
        return new GenricException(ErrorCodeEnum.TEACHER_CODE_EXIST.getCode()
                ,ErrorCodeEnum.TEACHER_CODE_EXIST.getText());
    }

    public static GenricException generateClassNumberError(){
        return new GenricException(ErrorCodeEnum.CLASS_NUMBER_EXIST.getCode()
                ,ErrorCodeEnum.CLASS_NUMBER_EXIST.getText());
    }

    public static GenricException generateCourseNumberError(){
        return new GenricException(ErrorCodeEnum.COURSE_NUMBER_EXIST.getCode()
                ,ErrorCodeEnum.COURSE_NUMBER_EXIST.getText());
    }

    public static GenricException generateMajorNumberError(){
        return new GenricException(ErrorCodeEnum.MAJOR_NUMBER_EXIST.getCode()
                ,ErrorCodeEnum.MAJOR_NUMBER_EXIST.getText());
    }

    public static GenricException generateChooseCourseError(){
        return new GenricException(ErrorCodeEnum.STU_COURSE_EXIST.getCode()
                ,ErrorCodeEnum.STU_COURSE_EXIST.getText());
    }

    public static GenricException generateCourseNotEnoughError(){
        return new GenricException(ErrorCodeEnum.COURSE_NOT_ENOUGH.getCode()
                ,ErrorCodeEnum.COURSE_NOT_ENOUGH.getText());
    }

    public static GenricException generateStudentCourseNotExistError(){
        return new GenricException(ErrorCodeEnum.STU_COURSE_NOT_EXIST.getCode()
                ,ErrorCodeEnum.STU_COURSE_NOT_EXIST.getText());
    }

    public static GenricException generateScoreAlreadyExistError(){
        return new GenricException(ErrorCodeEnum.SCORE_ALREADY_EXIST.getCode()
                ,ErrorCodeEnum.SCORE_ALREADY_EXIST.getText());
    }
}
