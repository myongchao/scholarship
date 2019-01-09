package com.myc.scholarship.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.myc.scholarship.entity.Record;
import com.myc.scholarship.service.RecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String,List<Record>> list(){
        Map<String,List<Record>> map = new HashMap<>();
        List<Record> records = recordService.selectList(new EntityWrapper<Record>());
        map.put("data",records);
        return map;
    }
}