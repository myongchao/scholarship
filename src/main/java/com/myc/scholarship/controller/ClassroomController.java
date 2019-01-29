package com.myc.scholarship.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.myc.scholarship.entity.Classroom;
import com.myc.scholarship.entity.jsonUtil.JsonResultEntity;
import com.myc.scholarship.entity.jsonUtil.JsonResultUtils;
import com.myc.scholarship.mian.entity.CommonSearchDto;
import com.myc.scholarship.service.ClassroomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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

    @ApiOperation(value = "获取单个班级信息")
    @GetMapping(value = "get")
    public JsonResultEntity getClassroom(@RequestParam("id")Long id){
        Classroom classroom = service.selectById(id);
        JsonResultEntity resultEntity = JsonResultUtils.success(classroom);
        return resultEntity;
    }

    @ApiOperation(value = "添加班级信息")
    @PostMapping(value = "add")
    public JsonResultEntity add(@RequestBody Classroom classroom){
        JsonResultEntity resultEntity = JsonResultUtils.success(service.insert(classroom));
        return resultEntity;
    }

    @ApiOperation(value = "删除班级信息")
    @PostMapping(value = "del")
    public JsonResultEntity del(@RequestParam("id")Long id){
        JsonResultEntity resultEntity = JsonResultUtils.success(service.deleteById(id));
        return resultEntity;
    }

    @ApiOperation(value = "分页获取班级列表")
    @PostMapping(value = "pageClassroom")
    public JsonResultEntity page(@RequestBody CommonSearchDto<Classroom> searchDto) throws JSONException {
        Page<Classroom> page = service.selectPageWithTeacher(searchDto.getPlusPage(),searchDto.formToEntityWrapperWithSearch(new String[]{"name"},"a."));
        JsonResultEntity resultEntity = JsonResultUtils.success(page);
        return resultEntity;
    }

}

