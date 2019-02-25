package com.myc.scholarship.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.myc.scholarship.entity.Record;
import com.myc.scholarship.entity.Student;
import com.myc.scholarship.entity.User;
import com.myc.scholarship.entity.dto.ImportRecordDto;
import com.myc.scholarship.entity.dto.ImportStudentDto;
import com.myc.scholarship.entity.dto.ListRecordDto;
import com.myc.scholarship.entity.dto.ListStudentDto;
import com.myc.scholarship.entity.jsonUtil.JsonResultEntity;
import com.myc.scholarship.entity.jsonUtil.JsonResultUtils;
import com.myc.scholarship.mian.entity.CommonSearchDto;
import com.myc.scholarship.service.RecordService;
import com.myc.scholarship.service.StudentService;
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

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
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

    @Autowired
    private StudentService studentService;

    private final static String templatePath = "/template/scholarshipApply.xlsx";


    @ApiOperation(value = "提交申请",notes = "提交申请")
    @RequestMapping(value = "multipleAdd",method = RequestMethod.POST)
    public JsonResultEntity insertBatches(@RequestBody @Validated ListRecordDto recordDto) {
        JsonResultEntity resultEntity = new JsonResultEntity();
        Date date = new Date();
        for (Record r:recordDto.getData()) {
            List<Record> records = recordService.selectList(new EntityWrapper<Record>().eq("num",r.getNum()));
            if(records.size()>0){
                resultEntity = JsonResultUtils.success(true);
                resultEntity.setData("已提交过申请！");
            }else {
                r.setCheck1("未审核");
                r.setCheck2("未审核");
                r.setInsertTime(date);
                Boolean success = recordService.insert(r);
                if (success) {
                    resultEntity = JsonResultUtils.success(success);
                }
            }
        }
        return resultEntity;
    }

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

    @ApiOperation(value = "教务处更新")
    @PostMapping(value = "/editManager")
    @ResponseBody
    public JsonResultEntity editManager(@RequestParam("id")Long id,@RequestParam("check")String check){
        Record record = recordService.selectById(id);
        record.setCheck2(check);
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

    @ApiOperation(value = "申请表模板下载", notes = "申请表模板下载")
    @GetMapping("/download/template")
    public JsonResultEntity download() {
        JsonResultEntity resultEntity = new JsonResultEntity();
        String path = templatePath;
        resultEntity = JsonResultUtils.success(path);
        return resultEntity;
    }

    @ApiOperation(value = "解析Excel文件",notes = "解析Excel文件")
    @PostMapping(value = "/import")
    @ResponseBody
    public JsonResultEntity upLoad(MultipartFile file){
        JsonResultEntity resultEntity = new JsonResultEntity();
        String[] a = {"name","num","className","depName","awardName","totalScore","totalSubjectScore","rank","status"};
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
        List<ImportRecordDto> recordDtos = recordService.importDataToDo(mapList);
        resultEntity = JsonResultUtils.success(recordDtos);
        return resultEntity;
    }

}