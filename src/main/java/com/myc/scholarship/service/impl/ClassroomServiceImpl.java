package com.myc.scholarship.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.myc.scholarship.entity.Classroom;
import com.myc.scholarship.mapper.ClassroomMapper;
import com.myc.scholarship.service.ClassroomService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Juci123
 * @since 2019-01-12
 */
@Service
public class ClassroomServiceImpl extends ServiceImpl<ClassroomMapper, Classroom> implements ClassroomService {

    @Override
    public Page<Classroom> selectPageWithTeacher(Page<Classroom> plusPage, Wrapper<Classroom> wrapper) {
        List<Classroom> classroomList = baseMapper.selectPageWithTeacher(plusPage,wrapper.eq("a.isdeleted",0));
        plusPage.setRecords(classroomList);
        return plusPage;
    }
}
