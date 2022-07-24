package com.hanhui.stumanage.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanhui.stumanage.model.Student;
import com.hanhui.stumanage.model.Teacher;
import com.hanhui.stumanage.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/Teacher")
public class TeacherController {

    @Resource
    private TeacherService teacherService;


    @PostMapping()
    public ResponseEntity<Teacher> register(@RequestBody Teacher teacher){
        return ResponseEntity.ok(teacherService.insert(teacher));
    }

    @GetMapping()
    public ResponseEntity<IPage<Teacher>> findList(Teacher teacher, Page page){
        return ResponseEntity.ok(teacherService.findList(teacher,page));
    }


    @GetMapping("/{teachId}")
    public ResponseEntity<Teacher> findById( @PathVariable("teachId") Long teachId){
        return ResponseEntity.ok(teacherService.findById(teachId));
    }

    @PutMapping("/{teachId}")
    public ResponseEntity<Teacher> updateById( @PathVariable("teachId") Long teachId,@RequestBody Teacher teacher){
        teacher.setTeachId(teachId);
        return ResponseEntity.ok(teacherService.updateById(teacher));
    }
}
