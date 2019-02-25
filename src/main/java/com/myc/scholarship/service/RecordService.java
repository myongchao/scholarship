package com.myc.scholarship.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.myc.scholarship.entity.Record;
import com.baomidou.mybatisplus.service.IService;
import com.myc.scholarship.entity.dto.ImportRecordDto;

import java.util.List;
import java.util.Map;

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

    Page<Record> pageWithAwardAndScore(Page<Record> plusPage, Wrapper<Record> formToEntityWrapperWithSearch);

    List<ImportRecordDto> importDataToDo(List<Map<String, Object>> mapList);
}
