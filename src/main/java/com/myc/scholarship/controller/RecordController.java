package com.myc.scholarship.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.myc.scholarship.entity.Record;
import com.myc.scholarship.service.RecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Juci123
 * @since 2019-01-08
 */
@RestController
@RequestMapping("/record")
@Api(description = "申请信息记录测试",value = "测试")
public class RecordController implements Serializable {

    @Autowired
    private RecordService recordService;

    @ApiOperation(value = "更新")
    @PostMapping(value = "/edit")
    @ResponseBody
    public Boolean edit(@RequestBody Record record){
        return recordService.updateById(record);
    }

    @ApiOperation(value = "申请信息列表")
    @GetMapping(value = "/list")
    @ResponseBody
    public List<Record> list(){
        List<Record> records = recordService.selectList(new EntityWrapper<Record>());
        return records;
    }
}