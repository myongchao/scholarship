package com.myc.scholarship.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.myc.scholarship.entity.Teacher;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Juci123
 * @since 2019-01-28
 */
public interface TeacherService extends IService<Teacher> {

    Page<Teacher> selectWithClassWithDep(Page<Teacher> plusPage, Wrapper<Teacher> formToEntityWrapperWithSearch);
}
