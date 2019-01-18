package com.myc.scholarship.mapper;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.myc.scholarship.entity.Score;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Juci123
 * @since 2019-01-10
 */
public interface ScoreMapper extends BaseMapper<Score> {

    List<Score> selectWithSubject(Page<Score> plusPage, @Param("ew") Wrapper<Score> formToEntityWrapperWithSearch);
}
