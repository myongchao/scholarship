package com.myc.scholarship.mapper;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.myc.scholarship.entity.Teacher;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Juci123
 * @since 2019-01-28
 */
public interface TeacherMapper extends BaseMapper<Teacher> {

    List<Teacher> selectWithClassWithDep(Page<Teacher> plusPage,@Param("ew") Wrapper<Teacher> wrapper);
}
