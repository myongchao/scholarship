package com.myc.scholarship.mapper;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.myc.scholarship.entity.Classroom;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Juci123
 * @since 2019-01-12
 */
public interface ClassroomMapper extends BaseMapper<Classroom> {

    List<Classroom> selectPageWithTeacher(Page<Classroom> plusPage,@Param("ew") Wrapper<Classroom> wrapper);
}
