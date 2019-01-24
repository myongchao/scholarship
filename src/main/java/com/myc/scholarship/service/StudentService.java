package com.myc.scholarship.service;


import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.myc.scholarship.entity.Student;
import com.myc.scholarship.entity.dto.ImportStudentDto;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Juci123
 * @since 2018-11-25
 */
public interface StudentService extends IService<Student>{

    Page<Student> selectWithClassAndDep(Page<Student> plusPage, Wrapper<Student> formToEntityWrapperWithSearch);

    Page<Student> selectWithSubject(Page<Student> plusPage, Wrapper<Student> formToEntityWrapperWithSearch);

    List<ImportStudentDto> importDataToDo(List<Map<String, Object>> mapList);
}
