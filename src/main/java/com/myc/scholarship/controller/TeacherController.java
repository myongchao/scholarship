package com.myc.scholarship.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.myc.scholarship.entity.Teacher;
import com.myc.scholarship.entity.User;
import com.myc.scholarship.entity.jsonUtil.JsonResultEntity;
import com.myc.scholarship.entity.jsonUtil.JsonResultUtils;
import com.myc.scholarship.mian.entity.CommonSearchDto;
import com.myc.scholarship.service.TeacherService;
import com.myc.scholarship.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Juci123
 * @since 2019-01-28
 */
@RestController
@RequestMapping("/teacher")
@Api(value = "教师管理")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "添加教师用户")
    @PostMapping(value = "add")
    public JsonResultEntity Add(@RequestBody Teacher teacher){
        JsonResultEntity resultEntity = new JsonResultEntity();
        User user = new User();
        Date date = new Date();
        teacher.setInsertTime(date);
        user.setNum(teacher.getNum());
        user.setCode(teacher.getNum());
        user.setPwd(teacher.getPwd());
        user.setName(teacher.getName());
        user.setClassId(teacher.getClassId());
        user.setDepId(teacher.getDepId());
        user.setRole("辅导员");
        Boolean b = userService.insert(user);
        Boolean success = teacherService.insert(teacher);
        if(success && b){
            resultEntity = JsonResultUtils.success(success);
        }
        return resultEntity;
    }

    @ApiOperation(value = "批量添加教师用户")
    @PostMapping(value = "multipleAdd")
    public JsonResultEntity multipleAdd(@RequestBody List<Teacher> teachers){
        JsonResultEntity resultEntity = new JsonResultEntity();
        Boolean success = teacherService.insertBatch(teachers);
        if(success){
            resultEntity = JsonResultUtils.success(success);
        }
        return resultEntity;
    }

    @ApiOperation(value = "获取单个教师信息")
    @PostMapping(value = "del")
    public JsonResultEntity del(@RequestParam("id")Long id){
        JsonResultEntity resultEntity = new JsonResultEntity();
        resultEntity = JsonResultUtils.success(teacherService.deleteById(id));
        return resultEntity;
    }

    @ApiOperation(value = "获取单个教师信息")
    @GetMapping(value = "get")
    public JsonResultEntity getTeacher(@RequestParam("id")Long id){
        Teacher teacher = teacherService.selectById(id);
        JsonResultEntity resultEntity = new JsonResultEntity();
        resultEntity = JsonResultUtils.success(teacher);
        return resultEntity;
    }

    @ApiOperation(value = "获取单个教师信息")
    @PostMapping(value = "edit")
    public JsonResultEntity edit(@RequestBody Teacher teacher){
        JsonResultEntity resultEntity = new JsonResultEntity();
        resultEntity = JsonResultUtils.success(teacherService.updateById(teacher));
        return resultEntity;
    }

    @ApiOperation(value = "教师分页")
    @PostMapping(value = "pageTeacher")
    public JsonResultEntity pageTeacher(@RequestBody CommonSearchDto<Teacher> searchDto) throws JSONException {
        Page<Teacher> page = teacherService.selectWithClassWithDep(searchDto.getPlusPage(),searchDto.formToEntityWrapperWithSearch(new String[]{"name"},"a."));
        JsonResultEntity resultEntity = new JsonResultEntity();
        resultEntity = JsonResultUtils.success(page);
        return resultEntity;
    }

}

