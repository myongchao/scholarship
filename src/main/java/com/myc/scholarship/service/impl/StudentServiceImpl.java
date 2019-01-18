package com.myc.scholarship.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.myc.scholarship.entity.Student;
import com.myc.scholarship.mapper.StudentMapper;
import com.myc.scholarship.service.StudentService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
