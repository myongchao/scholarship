package com.myc.scholarship.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.myc.scholarship.entity.Record;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Juci123
 * @since 2019-01-08
 */
public interface RecordService extends IService<Record> {

    List<Record> selectWithAward();
    Record myc();

    Record m();
}
