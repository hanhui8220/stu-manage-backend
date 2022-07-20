package com.hanhui.stumanage.service;

import com.hanhui.stumanage.dao.StudentDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentService {

    @Resource
    private StudentDao studentDao;
}
