package com.myc.scholarship.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.myc.scholarship.entity.Classroom;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Juci123
 * @since 2019-01-12
 */
public interface ClassroomService extends IService<Classroom> {

    Page<Classroom> selectPageWithTeacher(Page<Classroom> plusPage, Wrapper<Classroom> formToEntityWrapperWithSearch);
}
