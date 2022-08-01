package com.hanhui.stumanage.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanhui.stumanage.model.Course;
import com.hanhui.stumanage.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/Course")
public class CourseController {

    @Resource
    private CourseService courseService;

    @PostMapping()
    public ResponseEntity<Course> create(@RequestBody Course course){
        return ResponseEntity.ok(courseService.insert(course));
    }

    @GetMapping()
    public ResponseEntity<IPage<Course>> findList(Course course, Page page){
        return ResponseEntity.ok(courseService.findList(course,page));
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> findById( @PathVariable("courseId") Integer courseId){
        return ResponseEntity.ok(courseService.findById(courseId));
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Course> updateById(@RequestBody Course course,@PathVariable("courseId") Integer courseId){
        course.setCourseId(courseId);
        return ResponseEntity.ok(courseService.updateById(course));
    }
}
