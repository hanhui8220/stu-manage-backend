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
}
