package com.hanhui.stumanage.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanhui.stumanage.model.Major;
import com.hanhui.stumanage.service.MajorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/Major")
public class MajorController {
    
    @Resource
    private MajorService majorService;

    @PostMapping()
    public ResponseEntity<Major> create(@RequestBody Major major){
        return ResponseEntity.ok(majorService.insert(major));
    }

    @GetMapping()
    public ResponseEntity<IPage<Major>> findList(Major major, Page page){
        return ResponseEntity.ok(majorService.findList(major,page));
    }

    @GetMapping("/{majorId}")
    public ResponseEntity<Major> findById( @PathVariable("majorId") Integer majorId){
        return ResponseEntity.ok(majorService.findById(majorId));
    }

    @PutMapping("/{majorId}")
    public ResponseEntity<Major> updateById(@RequestBody Major major,@PathVariable("majorId") Integer majorId){
        major.setMajorId(majorId);
        return ResponseEntity.ok(majorService.updateById(major));
    }
}
