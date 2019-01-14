package com.myc.scholarship.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.myc.scholarship.entity.Award;
import com.myc.scholarship.mapper.AwardMapper;
import com.myc.scholarship.service.AwardService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Juci123
 * @since 2018-12-01
 */
@Service
public class AwardServiceImpl extends ServiceImpl<AwardMapper, Award> implements AwardService {

    /**
    * @description  分页与模糊查询
    * @author 马勇超
    * @date 2019/1/14 10:42
    * @param
    * @return
    */
    @Override
    public Page<Award> pageWithSearch(Page<Award> plusPage, Wrapper<Award> formToEntityWrapperWithSearch) {
        List<Award> awards = this.baseMapper.pageWithSearch(plusPage,formToEntityWrapperWithSearch.eq("isDeleted",0));
        plusPage.setRecords(awards);
        return plusPage;
    }
}
