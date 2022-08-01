package com.hanhui.stumanage.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanhui.stumanage.model.CourseStudent;
import com.hanhui.stumanage.model.Student;
import com.hanhui.stumanage.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/Student")
public class StudentController {

    @Resource
    private StudentService studentService;



    @PostMapping()
    public ResponseEntity<Student> register(@RequestBody Student student){
        return ResponseEntity.ok(studentService.insert(student));
    }

    @GetMapping()
    public ResponseEntity<IPage<Student>> findList(Student student, Page page){
        return ResponseEntity.ok(studentService.findList(student,page));
    }


    @GetMapping("/{stuId}")
    public ResponseEntity<Student> findById( @PathVariable("stuId") Long stuId){
        return ResponseEntity.ok(studentService.findById(stuId));
    }

    @PutMapping("/{stuId}")
    public ResponseEntity<Student> updateById( @PathVariable("stuId") Long stuId,@RequestBody Student student){
        student.setStuId(stuId);
        return ResponseEntity.ok(studentService.updateById(student));
    }

    @PutMapping("/Course/choose")
    public ResponseEntity<CourseStudent> chooseCourse(@RequestBody CourseStudent courseStudent ){
        return ResponseEntity.ok(studentService.chooseCourse(courseStudent));
    }
}
