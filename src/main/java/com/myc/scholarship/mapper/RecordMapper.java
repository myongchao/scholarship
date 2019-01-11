package com.myc.scholarship.mapper;

import com.myc.scholarship.entity.Record;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Juci123
 * @since 2019-01-08
 */
public interface RecordMapper extends BaseMapper<Record> {

    List<Record> selectWithAward();

    Record myc();
}
