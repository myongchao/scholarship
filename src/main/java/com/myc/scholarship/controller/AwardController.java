package com.myc.scholarship.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.myc.scholarship.entity.Award;
import com.myc.scholarship.entity.PageDto;
import com.myc.scholarship.entity.jsonUtil.JsonResultEntity;
import com.myc.scholarship.entity.jsonUtil.JsonResultUtils;
import com.myc.scholarship.mian.entity.CommonSearchDto;
import com.myc.scholarship.service.AwardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.util.List;

/**
* Copyright (C), 2018 - 2019, ZhengZhouChuangZhi. Co., L td.
* FileName: AwardController.java
* 类的详细说明
*
* @author 马勇超
* @date 2018/12/1 16:06
* @version 1.0
*/
@RestController
@RequestMapping("/award")
@Api(value = "Award Controller",description = "奖项模块")
public class AwardController implements Serializable{

    @Autowired
    private AwardService awardService;

    @ApiOperation(value = "根据id获取奖项内容",notes = "根据id获取奖项内容")
    @GetMapping(value = "/get/{id}")
    @ResponseBody
    public Award getById(@PathVariable("id") Long id){
        Award award = awardService.selectById(id);
        return award;
    }


    @ApiOperation(value = "增加奖项",notes = "增加奖项")
    @PostMapping(value = "/add")
    @ResponseBody
    public Award add(@RequestBody Award award){
        awardService.insert(award);
        return  award;
    }

    @ApiOperation(value = "更改奖项",notes = "更改奖项")
    @PutMapping(value = "/edit")
    @ResponseBody
    public Award update(@RequestBody Award award){
        awardService.updateById(award);
        return  award;
    }

    @ApiOperation(value = "删除奖项",notes = "删除奖项")
    @DeleteMapping(value = "/del/{id}")
    @ResponseBody
    public Boolean delete(@PathVariable("id") Long id){
       Boolean success =  awardService.deleteById(id);
        return  success;
    }

    @ApiOperation(value = "所有奖学金类型",notes = "所有奖学金类型")
    @GetMapping(value = "/list")
    @ResponseBody
    public JsonResultEntity list(){
        List<Award> awards = awardService.selectList(new EntityWrapper<>());
        JsonResultEntity resultEntity = JsonResultUtils.success(awards);
        return  resultEntity;
    }

    @ApiOperation(value = "分页",notes = "分页")
    @PostMapping(value = "/page")
    @ResponseBody
    public JsonResultEntity page(@RequestBody CommonSearchDto<Award> searchDto) throws JSONException {
        Page<Award> awardPage = awardService.pageWithSearch(searchDto.getPlusPage(),searchDto.formToEntityWrapperWithSearch(new String[]{"id"},null));
        JsonResultEntity resultEntity = JsonResultUtils.success(awardPage);
        return  resultEntity;
    }
}

