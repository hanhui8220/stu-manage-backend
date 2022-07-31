package com.hanhui.stumanage.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanhui.stumanage.model.ClassInfo;
import com.hanhui.stumanage.model.Student;
import com.hanhui.stumanage.service.ClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ClassInfo")
public class ClassController {

    @Resource
    private ClassService classService;



    @PostMapping()
    public ResponseEntity<ClassInfo> create(@RequestBody ClassInfo classInfo){
        return ResponseEntity.ok(classService.insert(classInfo));
    }

    @GetMapping()
    public ResponseEntity<IPage<ClassInfo>> findList(ClassInfo classInfo, Page page){
        return ResponseEntity.ok(classService.findList(classInfo,page));
    }

    @GetMapping("/{classId}")
    public ResponseEntity<ClassInfo> findById( @PathVariable("classId") Long classId){
        return ResponseEntity.ok(classService.findById(classId));
    }

    @PutMapping("/{classId}")
    public ResponseEntity<ClassInfo> updateById(@RequestBody ClassInfo classInfo,@PathVariable("classId") Integer classId){
        classInfo.setClassId(classId);
        return ResponseEntity.ok(classService.updateById(classInfo));
    }

    @GetMapping("/Student")
    public ResponseEntity<Map<String,Object>> findStudenByClassNumber(@RequestParam("classNumber") String classNumber){
        return ResponseEntity.ok(classService.findStudenByClassNumber(classNumber));
    }

    @PostMapping("/Student/assign")
    public ResponseEntity<ClassInfo> assignStudent(@RequestBody ClassInfo classInfo){
        return ResponseEntity.ok(classService.assignStudent(classInfo));
    }
}
