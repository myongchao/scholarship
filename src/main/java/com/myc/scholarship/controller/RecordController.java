package com.myc.scholarship.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.myc.scholarship.entity.Record;
import com.myc.scholarship.entity.Student;
import com.myc.scholarship.entity.jsonUtil.JsonResultEntity;
import com.myc.scholarship.entity.jsonUtil.JsonResultUtils;
import com.myc.scholarship.mian.entity.CommonSearchDto;
import com.myc.scholarship.service.RecordService;
import com.myc.scholarship.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.Date;
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

    @Autowired
    private StudentService studentService;

    @ApiOperation(value = "提交申请保存")
    @PostMapping(value = "add")
    public JsonResultEntity add(@RequestBody Record record){
        JsonResultEntity resultEntity = new JsonResultEntity();
        Date date = new Date();
        List<Record> records = recordService.selectList(new EntityWrapper<Record>().eq("num",record.getNum()));
        if(records.size()>0){
            resultEntity = JsonResultUtils.success(true);
            resultEntity.setData("已提交过申请！");
        }else {
            Student student = studentService.selectOne(new EntityWrapper<Student>().eq("num", record.getNum()));
            record.setName(student.getName());
            record.setCheck1("未审核");
            record.setCheck2("未审核");
            record.setInsertTime(date);
            Boolean success = recordService.insert(record);
            if (success) {
                resultEntity = JsonResultUtils.success(success);
            }
        }
        return resultEntity;
    }

    @ApiOperation(value = "更新")
    @PostMapping(value = "/edit")
    @ResponseBody
    public JsonResultEntity edit(@RequestParam("id")Long id,@RequestParam("check")String check){
        Record record = recordService.selectById(id);
        record.setCheck1(check);
        Boolean success = recordService.updateById(record);
        JsonResultEntity resultEntity = new JsonResultEntity();
        if(success){
            resultEntity = JsonResultUtils.success(success);
        }
        return resultEntity;
    }

    @ApiOperation(value = "删除单个申请信息")
    @PostMapping(value = "del")
    public JsonResultEntity del(@RequestParam("id")Long id){
        Record record = recordService.selectById(id);
        JsonResultEntity resultEntity = new JsonResultEntity();
        if(record.getCheck1().equals("通过") || record.getCheck2().equals("通过")){
            resultEntity = JsonResultUtils.success(true);
            resultEntity.setData("删除失败！该申请已审核！");
        }else {
            Boolean b = recordService.deleteById(id);
            resultEntity = JsonResultUtils.success(b);
        }
        return resultEntity;
    }

    @ApiOperation(value = "申请信息列表")
    @GetMapping(value = "/list")
    @ResponseBody
    public JsonResultEntity list(){
        List<Record> records = recordService.selectWithAward();
        JsonResultEntity resultEntity = JsonResultUtils.success(records);
        return resultEntity;
    }

    @ApiOperation(value = "分页")
    @PostMapping("/pageRecord")
    public JsonResultEntity page(@RequestBody CommonSearchDto<Record> searchDto) throws JSONException {
        JsonResultEntity resultEntity = new JsonResultEntity();
        Page<Record> recordPage = recordService.pageWithAwardAndScore(searchDto.getPlusPage(),
                searchDto.formToEntityWrapperWithSearch(new String[]{"awardId","classId","check1","num"},"a."));
        resultEntity = JsonResultUtils.success(recordPage);
        return resultEntity;
    }

}