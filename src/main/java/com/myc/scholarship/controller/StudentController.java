package com.myc.scholarship.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.myc.scholarship.entity.PageDto;
import com.myc.scholarship.entity.Student;
import com.myc.scholarship.mapper.StudentMapper;
import com.myc.scholarship.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
* Copyright (C), 2018 - 2019, ZhengZhouChuangZhi. Co., L td. 
* FileName: StudentController.java 
* 类的详细说明 
* 
* @author 马勇超
* @date 2018/11/30 12:25
* @version 1.0 
*/ 
 
@RestController
@RequestMapping("/student")
@Api(description = "测试模块", value = "测试模块")
public class StudentController implements Serializable {


    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentMapper studentMapper;

    @ApiOperation(value = "创建",notes = "创建")
    @PostMapping(value = "/create")
    @ResponseBody
    public Student create(@RequestBody Student student){

        studentMapper.insert(student);
        return student;
    }

    @ApiOperation(value = "根据id删除",notes = "根据id删除")
    @DeleteMapping(value = "/del/{id}")
    @ResponseBody
    public void delete(@RequestBody Long id){
        studentMapper.deleteById(id);
    }

    @ApiOperation(value = "修改",notes = "修改")
    @PutMapping(value = "/edit")
    @ResponseBody
    public Student update(@RequestBody Student student){
        studentMapper.updateById(student);
        return student;
    }

    @ApiOperation(value = "获取",notes = "获取")
    @GetMapping(value = "/list")
    public List<Student> getAll(){
        List<Student> students = studentMapper.selectList(new EntityWrapper<Student>());
        return students;
    }

    @ApiOperation(value = "分页",notes = "分页")
    @PostMapping(value = "/page")
    @ResponseBody
    public Page<Student> studentPage(@RequestBody PageDto pageDto){
        Page<Student> page = new Page<Student>(
                pageDto.getCurrent(),
                pageDto.getSize(),
                pageDto.getOrder(),
                false
        );
       studentService.selectPage(page,new EntityWrapper<Student>());
        return page;
    }

}

