package com.myc.scholarship.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.myc.scholarship.entity.PageDto;
import com.myc.scholarship.entity.Student;
import com.myc.scholarship.entity.jsonUtil.JsonResultEntity;
import com.myc.scholarship.entity.jsonUtil.JsonResultUtils;
import com.myc.scholarship.mian.entity.CommonSearchDto;
import com.myc.scholarship.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONException;
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

    @ApiOperation(value = "创建", notes = "创建")
    @PostMapping(value = "/create")
    @ResponseBody
    public Student create(@RequestBody Student student) {

        studentService.insert(student);
        return student;
    }

    @ApiOperation(value = "根据id删除", notes = "根据id删除")
    @PostMapping(value = "/del")
    @ResponseBody
    public JsonResultEntity delete(@RequestParam("id") Long id) {
        JsonResultEntity resultEntity = new JsonResultEntity();
        Boolean success = studentService.deleteById(id);
        if(success){
            resultEntity = JsonResultUtils.success(success);
        }
        return resultEntity;
    }

    @ApiOperation(value = "修改", notes = "修改")
    @PostMapping(value = "/edit")
    @ResponseBody
    public JsonResultEntity update(@RequestBody Student student) {
        Boolean success = studentService.updateById(student);
        JsonResultEntity resultEntity = new JsonResultEntity();
        if(success){
            resultEntity = JsonResultUtils.success(success);
        }
        return resultEntity;
    }

    @ApiOperation(value = "获取单个学生信息", notes = "获取")
    @GetMapping(value = "/get")
    public JsonResultEntity get(@RequestParam("id")Long id) {
        Student student = studentService.selectById(id);
        JsonResultEntity resultEntity = JsonResultUtils.success(student);
        return resultEntity;
    }


    @ApiOperation(value = "获取", notes = "获取")
    @GetMapping(value = "/list")
    public List<Student> getAll() {
        List<Student> students = studentService.selectList(new EntityWrapper<Student>());
        return students;
    }

    @ApiOperation(value = "分页")
    @PostMapping("/page")
    public JsonResultEntity page(@RequestBody CommonSearchDto<Student> searchDto) throws JSONException {
        Page<Student> page = studentService.selectWithClassAndDep(searchDto.getPlusPage(), searchDto.formToEntityWrapperWithSearch(new String[]{"name", "num", "class_id"}, "a."));
        JsonResultEntity resultEntity = JsonResultUtils.success(page);
        return resultEntity;
    }

//    @ApiOperation(value = "分页查询成绩")
//    @PostMapping(value = "/pageWithSubject")
//    public JsonResultEntity pageWithSubject(@RequestBody CommonSearchDto<Student> searchDto) throws JSONException {
//        Page<Student> pageSubject = studentService.selectWithSubject(searchDto.getPlusPage(), searchDto.formToEntityWrapperWithSearch(new String[]{"name", "class_id"}, "a."));
//        JsonResultEntity resultEntity = JsonResultUtils.success(pageSubject);
//        return resultEntity;
//    }
}


