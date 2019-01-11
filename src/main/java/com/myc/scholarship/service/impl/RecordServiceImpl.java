package com.myc.scholarship.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.myc.scholarship.entity.Record;
import com.myc.scholarship.mapper.RecordMapper;
import com.myc.scholarship.service.RecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Juci123
 * @since 2019-01-08
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public List<Record> selectWithAward() {
        return recordMapper.selectWithAward();
    }

    @Override
    public Record myc() {
        return recordMapper.myc();
    }

    @Override
    public Record m() {
        return this.baseMapper.selectById(1);
    }
}
