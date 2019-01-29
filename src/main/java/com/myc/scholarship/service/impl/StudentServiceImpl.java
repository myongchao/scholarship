package com.myc.scholarship.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.myc.scholarship.entity.Classroom;
import com.myc.scholarship.entity.Department;
import com.myc.scholarship.entity.Student;
import com.myc.scholarship.entity.Teacher;
import com.myc.scholarship.entity.dto.ImportStudentDto;
import com.myc.scholarship.mapper.StudentMapper;
import com.myc.scholarship.service.ClassroomService;
import com.myc.scholarship.service.DepartmentService;
import com.myc.scholarship.service.StudentService;
import com.myc.scholarship.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
 * @since 2018-11-25
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper,Student> implements StudentService {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private Validator defaultValidator;
    /**
    * @description  分页
    * @author 马勇超
    * @date 2019/1/17 17:46
    * @param
    * @return
    */
    @Override
    public Page<Student> selectWithClassAndDep(Page<Student> plusPage, Wrapper<Student> formToEntityWrapperWithSearch) {
        List<Student> students = baseMapper.selectWithClassAndDep(plusPage,formToEntityWrapperWithSearch.eq("a.isDeleted",false));
        plusPage.setRecords(students);
        return plusPage;
    }

    /**
    * @description  分页查询学生成绩
    * @author 马勇超
    * @date 2019/1/17 17:46
    * @param
    * @retur.
    */
    @Override
    public Page<Student> selectWithSubject(Page<Student> page, Wrapper<Student> studentWrapper) {
        List<Student> studentScore = baseMapper.selectWithSubject(page,studentWrapper.eq("a.isDeleted",false));
        page.setRecords(studentScore);
        return page;
    }

    @Override
    public List<ImportStudentDto> importDataToDo(List<Map<String, Object>> importData) {
        List<ImportStudentDto> studentDtos = new ArrayList<>();
        Field[] fields = Student.class.getDeclaredFields(); //反射获取专业类中的所有字段

        //查询数据库的数据
        Wrapper<Student> wrapper = new EntityWrapper<>();
        wrapper.setSqlSelect("num","class_id","depId").eq("isDeleted",false);
        List<Student> students = baseMapper.selectList(wrapper); // 数据库的数据

        for(Map<String, Object>  data : importData){
            ImportStudentDto studentDto = new ImportStudentDto();
            studentDto.setSuccess(true);
            List<String> tips = new ArrayList<>();
            //错误字段
            List<String> errorField = createByReflect(studentDto,fields,data);
            //验证返回列中是否存在
            //数据库重复检验
            if(studentDto.getName() != null && studentDto.getDepName()!= null && studentDto.getClassName() != null && studentDto.getTeacherName() != null){
                Boolean exit = studentDtos.stream().filter(b -> studentDto.getName().equals(b.getName())
                         && studentDto.getDepName().equals(b.getDepName()) && studentDto.getClassName().equals(b.getClassName()) ).count() > 0;
                if(exit) {continue;} //存在就直接跳出
                Department department = departmentService.selectOne(new EntityWrapper().eq("name",studentDto.getDepName()));
                Classroom classroom = classroomService.selectOne(new EntityWrapper<Classroom>().eq("name",studentDto.getClassName()));
                Teacher teacher = teacherService.selectOne(new EntityWrapper<Teacher>().eq("name",studentDto.getTeacherName()));
                if(department == null || classroom == null || teacher == null){
                    studentDto.setSuccess(true);
                    tips.add("本校不存在"+studentDto.getDepName()+"院系"+"或者"+ studentDto.getClassName()+"不存在"+"或者"+ studentDto.getTeacherName()+"不存在");
                }else {
                    exit = students.stream().filter(b -> studentDto.getName().equals(b.getName())
                            && department.getId().equals(b.getDepId()) && classroom.getId().equals(b.getClassId())&& teacher.getId().equals(b.getTeacherId())).count() > 0;
                    if (exit) {
                        studentDto.setSuccess(true);
                        tips.add(studentDto.getDepName() + "院系"+studentDto.getTeacherName()+"老师管理的"+studentDto.getClassName() +"班级中" + studentDto.getName() + "学生信息已存在");
                    }
                    studentDto.setDepId(department.getId());
                    studentDto.setClassId(classroom.getId());
                    studentDto.setTeacherId(teacher.getId());
                }
            }

            //字段转换失败
            if(errorField.size()>0){
                tips.add("字段转换失败：" + String.join(";",errorField));
                studentDto.setSuccess(false);
            }

            //验证
            Set<ConstraintViolation<ImportStudentDto>> violations = defaultValidator.validate(studentDto);//验证
            if(violations.size()>0){
                studentDto.setSuccess(false);
                String err = String.join(";",violations.stream().map(b ->{
                    return b.getMessage();
                }).collect(Collectors.joining()));
                tips.add("字段验证失败："+err);
            }
            studentDto.setTips(String.join(".",tips));
            studentDtos.add(studentDto);
        }
        return studentDtos;
    }
    /**
     * 创建通过反射
     * @param student
     * @param fields
     * @param data
     * @return List: 错误信息数据
     */
    private List<String> createByReflect(ImportStudentDto studentDto, Field[] fields, Map<String, Object> data) {
        List<String> errfiledList = new ArrayList<>();
        for (Field field : fields) {
            String key = field.getName();
            Object val = data.get(key);
            if (val == null) {continue;}
            try {
                //取消访问权限控制
                field.setAccessible(true);
                if (field.getType().equals(Integer.class)) {
                    field.set(studentDto, new Integer(data.get(key).toString()));
                } else {
                    field.set(studentDto, data.get(key));
                }
            } catch (IllegalAccessException e) {
                errfiledList.add(e.toString() + "填入失败");
            }
        }
        return errfiledList;
    }

}
