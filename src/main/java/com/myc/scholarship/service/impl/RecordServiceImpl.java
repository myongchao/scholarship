package com.myc.scholarship.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.myc.scholarship.entity.*;
import com.myc.scholarship.entity.dto.ImportRecordDto;
import com.myc.scholarship.entity.dto.ImportStudentDto;
import com.myc.scholarship.mapper.RecordMapper;
import com.myc.scholarship.service.AwardService;
import com.myc.scholarship.service.ClassroomService;
import com.myc.scholarship.service.DepartmentService;
import com.myc.scholarship.service.RecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Juci123
 * @since 2019-01-08
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private AwardService awardService;

    @Autowired
    private Validator defaultValidator;

    @Override
    public List<Record> selectWithAward() {
        return recordMapper.selectWithAward();
    }

    /**
    * @description  分页查询
    * @author 马勇超
    * @date 2019/1/14 9:45
    * @param
    * @return
    */
    @Override
    public Page<Record> pageWithAwardAndScore(Page<Record> plusPage, Wrapper<Record> formToEntityWrapperWithSearch) {
        List<Record> records = this.baseMapper.pageWithAwardAndScore(plusPage,formToEntityWrapperWithSearch.eq("a.isDeleted",0)
        .groupBy("a.id"));
        plusPage.setRecords(records);
        return plusPage;
    }

    @Override
    public List<ImportRecordDto> importDataToDo(List<Map<String, Object>> importData) {
        List<ImportRecordDto> recordDtos = new ArrayList<>();
        Field[] fields = Record.class.getDeclaredFields(); //反射获取申请记录中的所有字段

        //查询数据库的数据
        Wrapper<Record> wrapper = new EntityWrapper<>();
        wrapper.setSqlSelect("num","class_id","depId").eq("isDeleted",false);
        List<Record> records = baseMapper.selectList(wrapper); // 数据库的数据

        for(Map<String, Object>  data : importData){
            ImportRecordDto recordDto = new ImportRecordDto();
            recordDto.setSuccess(true);
            List<String> tips = new ArrayList<>();
            //错误字段
            List<String> errorField = createByReflect(recordDto,fields,data);
            //验证返回列中是否存在
            //数据库重复检验
            if(recordDto.getName() != null && recordDto.getDepName()!= null && recordDto.getClassName() != null){
                Boolean exit = recordDtos.stream().filter(b -> recordDto.getName().equals(b.getName())
                        && recordDto.getDepName().equals(b.getDepName()) && recordDto.getClassName().equals(b.getClassName()) ).count() > 0;
                if(exit) {continue;} //存在就直接跳出
                Department department = departmentService.selectOne(new EntityWrapper().eq("name",recordDto.getDepName()));
                Classroom classroom = classroomService.selectOne(new EntityWrapper<Classroom>().eq("name",recordDto.getClassName()));
                Award award = awardService.selectOne(new EntityWrapper<Award>().eq("title",recordDto.getAwardName()));
                if(department == null || classroom == null){
                    recordDto.setSuccess(true);
                    tips.add("本校不存在"+recordDto.getDepName()+"院系"+"或者"+ recordDto.getClassName()+"不存在");
                }else {
                    exit = records.stream().filter(b -> recordDto.getName().equals(b.getName())
                            && department.getId().equals(b.getDepId()) && classroom.getId().equals(b.getClassId())).count() > 0;
                    if (exit) {
                        recordDto.setSuccess(true);
                        tips.add( "申请信息已存在");
                    }
                    recordDto.setDepId(department.getId());
                    recordDto.setClassId(classroom.getId());
                    recordDto.setAwardId(award.getId());
                }
            }

            //字段转换失败
            if(errorField.size()>0){
                tips.add("字段转换失败：" + String.join(";",errorField));
                recordDto.setSuccess(false);
            }

            //验证
            Set<ConstraintViolation<ImportRecordDto>> violations = defaultValidator.validate(recordDto);//验证
            if(violations.size()>0){
                recordDto.setSuccess(false);
                String err = String.join(";",violations.stream().map(b ->{
                    return b.getMessage();
                }).collect(Collectors.joining()));
                tips.add("字段验证失败："+err);
            }
            recordDto.setTips(String.join(".",tips));
            recordDtos.add(recordDto);
        }
        return recordDtos;
    }
    /**
     * 创建通过反射
     * @param student
     * @param fields
     * @param data
     * @return List: 错误信息数据
     */
    private List<String> createByReflect(ImportRecordDto recordDto, Field[] fields, Map<String, Object> data) {
        List<String> errfiledList = new ArrayList<>();
        for (Field field : fields) {
            String key = field.getName();
            Object val = data.get(key);
            if (val == null) {continue;}
            try {
                //取消访问权限控制
                field.setAccessible(true);
                if (field.getType().equals(Integer.class)) {
                    field.set(recordDto, new Integer(data.get(key).toString()));
                } else {
                    field.set(recordDto, data.get(key));
                }
            } catch (IllegalAccessException e) {
                errfiledList.add(e.toString() + "填入失败");
            }
        }
        return errfiledList;
    }
}
