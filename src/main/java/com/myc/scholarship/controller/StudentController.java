package com.myc.scholarship.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.myc.scholarship.entity.PageDto;
import com.myc.scholarship.entity.Student;
import com.myc.scholarship.entity.User;
import com.myc.scholarship.entity.dto.ImportStudentDto;
import com.myc.scholarship.entity.dto.ListStudentDto;
import com.myc.scholarship.entity.jsonUtil.JsonResultEntity;
import com.myc.scholarship.entity.jsonUtil.JsonResultUtils;
import com.myc.scholarship.mian.entity.CommonSearchDto;
import com.myc.scholarship.service.StudentService;
import com.myc.scholarship.service.UserService;
import com.myc.scholarship.util.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    private UserService userService;

    private final static String templatePath = "/template/studentImportTemplate.xlsx";

    @ApiOperation(value = "学生模板下载", notes = "学生模板下载")
    @GetMapping("/download/template")
    public JsonResultEntity download() {
        JsonResultEntity resultEntity = new JsonResultEntity();
        String path = templatePath;
        resultEntity = JsonResultUtils.success(path);
        return resultEntity;
    }

    @ApiOperation(value = "创建", notes = "创建")
    @PostMapping(value = "/create")
    @ResponseBody
    public JsonResultEntity create(@RequestBody Student student) {
        User user = new User();
        user.setNum(student.getNum());
        user.setCode(student.getNum());
        user.setPwd("000000");
        user.setName(student.getName());
        user.setClassId(student.getClassId());
        user.setDepId(student.getDepId());
        user.setRole("学生");
        Boolean b = userService.insert(user);
        Boolean success = studentService.insert(student);
        JsonResultEntity resultEntity = new JsonResultEntity();
        if(success && b){
            resultEntity = JsonResultUtils.success(success);
        }
        return resultEntity;
    }

    @ApiOperation(value = "批量上传",notes = "批量上传")
    @RequestMapping(value = "multipleAdd",method = RequestMethod.POST)
    public JsonResultEntity insertBatches(@RequestBody @Validated ListStudentDto studentDto) {
        Boolean success = studentService.insertBatch(studentDto.getData());
        for (Student student:studentDto.getData()) {
            User user = new User();
            user.setNum(student.getNum());
            user.setCode(student.getNum());
            user.setPwd("000000");
            user.setName(student.getName());
            user.setClassId(student.getClassId());
            user.setDepId(student.getDepId());
            user.setRole("学生");
            Boolean b = userService.insert(user);
        }
        JsonResultEntity resultEntity = new JsonResultEntity();
        if(success){
            resultEntity = JsonResultUtils.success(success);
        }
        return resultEntity;
    }

    @ApiOperation(value = "解析Excel文件",notes = "解析Excel文件")
    @PostMapping(value = "/import")
    @ResponseBody
    public JsonResultEntity upLoad(MultipartFile file){
        JsonResultEntity resultEntity = new JsonResultEntity();
        String[] a = {"num","name","className","depName","teacherName","familyAccount","familyNum","address","zipCode","inCome"};
        List<String> fields = Arrays.asList(a);
        List<Map<String, Object>> mapList = null;
        try {
            Workbook workbook = null;
            String filename = file.getOriginalFilename();
            if (filename.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(file.getInputStream());
            }else if(filename.endsWith(".xls")) {
                workbook = new HSSFWorkbook(file.getInputStream());
            }
            mapList = ExcelUtil.readFilds(workbook, fields);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<ImportStudentDto> studentDtos = studentService.importDataToDo(mapList);
        resultEntity = JsonResultUtils.success(studentDtos);
        return resultEntity;
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


