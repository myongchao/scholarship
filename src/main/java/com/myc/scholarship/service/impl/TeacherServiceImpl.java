package com.myc.scholarship.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.myc.scholarship.entity.Teacher;
import com.myc.scholarship.mapper.TeacherMapper;
import com.myc.scholarship.service.TeacherService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Juci123
 * @since 2019-01-28
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public Page<Teacher> selectWithClassWithDep(Page<Teacher> plusPage, Wrapper<Teacher> wrapper) {
        List<Teacher> teachers = baseMapper.selectWithClassWithDep(plusPage,wrapper.eq("a.isDeleted",0));
        plusPage.setRecords(teachers);
        return plusPage;
    }
}
