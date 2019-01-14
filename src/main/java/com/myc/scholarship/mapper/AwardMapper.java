package com.myc.scholarship.mapper;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.myc.scholarship.entity.Award;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Juci123
 * @since 2018-12-01
 */
public interface AwardMapper extends BaseMapper<Award> {

    List<Award> pageWithSearch(Page<Award> plusPage,@Param("ew") Wrapper<Award> formToEntityWrapperWithSearch);
}
