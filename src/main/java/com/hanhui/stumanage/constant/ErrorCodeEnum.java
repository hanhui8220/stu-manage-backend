package com.hanhui.stumanage.constant;


import lombok.Getter;

@Getter
public enum  ErrorCodeEnum {

    USER_CODE_EXIST("T_USER_CODE_EXIST","用户账号已存在"),
    USER_INVALID("T_USER_INVALID","用户名或密码不正确");

    private String code;

    private String text;

    private ErrorCodeEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }
}