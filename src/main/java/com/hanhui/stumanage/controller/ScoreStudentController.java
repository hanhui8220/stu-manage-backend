package com.hanhui.stumanage.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanhui.stumanage.model.ScoreStudent;
import com.hanhui.stumanage.service.ScoreStudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/Score")
public class ScoreStudentController {

    @Resource
    private ScoreStudentService scoreStudentService;


    @PostMapping()
    public ResponseEntity<ScoreStudent> create(@RequestBody ScoreStudent scoreStudent){
        return ResponseEntity.ok(scoreStudentService.insert(scoreStudent));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ScoreStudent> findById( @PathVariable("id") Integer id){
        return ResponseEntity.ok(scoreStudentService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScoreStudent> updateById(@RequestBody ScoreStudent scoreStudent,@PathVariable("id") Integer id){
        scoreStudent.setId(id);
        return ResponseEntity.ok(scoreStudentService.updateById(scoreStudent));
    }

    @GetMapping()
    public ResponseEntity<IPage<ScoreStudent>> findList(ScoreStudent scoreStudent, Page page){
        return ResponseEntity.ok(scoreStudentService.findList(scoreStudent,page));
    }


    @DeleteMapping()
    public ResponseEntity<Integer> deleteByIds(@RequestBody List<String> ids){
        return ResponseEntity.ok(scoreStudentService.deleteByIds(ids));
    }


    @GetMapping("/self")
    public ResponseEntity<IPage<ScoreStudent>> fineSelf(ScoreStudent scoreStudent, Page page){
        return ResponseEntity.ok(scoreStudentService.findSelf(scoreStudent,page));
    }
}
