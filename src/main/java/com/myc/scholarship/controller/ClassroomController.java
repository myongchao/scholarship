package com.myc.scholarship.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.myc.scholarship.entity.Classroom;
import com.myc.scholarship.entity.jsonUtil.JsonResultEntity;
import com.myc.scholarship.entity.jsonUtil.JsonResultUtils;
import com.myc.scholarship.service.ClassroomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Juci123
 * @since 2019-01-12
 */
@RestController
@RequestMapping("/classroom")
@Api(value = "班级模块")
public class ClassroomController {

    @Autowired
    private ClassroomService service;

    @ApiOperation(value = "班级列表")
    @GetMapping(value = "/list")
    public JsonResultEntity classList(){
        JsonResultEntity resultEntity = new JsonResultEntity();
        List<Classroom> classrooms = service.selectList(new EntityWrapper<>());
        return JsonResultUtils.success(classrooms);
    }

}

