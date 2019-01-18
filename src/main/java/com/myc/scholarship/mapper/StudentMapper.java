package com.myc.scholarship.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.myc.scholarship.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Juci123
 * @since 2018-11-25
 */
public interface StudentMapper extends BaseMapper<Student> {

    List selectWithClassAndDep(Page<Student> plusPage,@Param("ew") Wrapper<Student> formToEntityWrapperWithSearch);

    List<Student> selectWithSubject(Page<Student> page,@Param("ew") Wrapper<Student> studentWrapper);
}
