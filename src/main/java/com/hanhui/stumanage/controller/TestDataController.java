package com.hanhui.stumanage.controller;


import com.hanhui.stumanage.service.TestDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/TestData")
public class TestDataController {

    @Resource
    private TestDataService testDataService;


    @GetMapping("/init")
    public ResponseEntity<Boolean> initTestData(){
        return ResponseEntity.ok(testDataService.initTestData());
    }
}
