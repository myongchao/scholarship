package com.myc.scholarship.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.myc.scholarship.entity.Score;
import com.myc.scholarship.mapper.ScoreMapper;
import com.myc.scholarship.service.ScoreService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Juci123
 * @since 2019-01-10
 */
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService {

    @Override
    public Page<Score> selectWithSubject(Page<Score> plusPage, Wrapper<Score> formToEntityWrapperWithSearch) {
        List<Score> scoreList = baseMapper.selectWithSubject(plusPage,formToEntityWrapperWithSearch.eq("a.isDeleted",0));
        plusPage.setRecords(scoreList);
        return plusPage;
    }

    @Override
    public Score selectWithStudentById(Long id) {
        return baseMapper.selectWithStudentById(id);
    }
}
