package com.myc.scholarship.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
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

    /**
    * @description  分页查询
    * @author 马勇超
    * @date 2019/1/14 9:45
    * @param
    * @return
    */
    @Override
    public Page<Record> pageWithAwardAndScore(Page<Record> plusPage, Wrapper<Record> formToEntityWrapperWithSearch) {
        List<Record> records = this.baseMapper.pageWithAwardAndScore(plusPage,formToEntityWrapperWithSearch.eq("a.isDeleted",false)
        .groupBy("a.id"));
        plusPage.setRecords(records);
        return plusPage;
    }
}
