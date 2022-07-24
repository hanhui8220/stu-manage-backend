package com.hanhui.stumanage.controller;


import com.hanhui.stumanage.constant.ErrorCodeEnum;
import com.hanhui.stumanage.exception.GenricException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {


    @ResponseBody
    @ExceptionHandler(value = GenricException.class)
    public ResponseEntity<Map> errorHandler(GenricException e) {
        Map map = new HashMap();
        map.put("code", e.getCode());
        map.put("msg", e.getMessage());
        return ResponseEntity.badRequest().body(map);
    }

}
