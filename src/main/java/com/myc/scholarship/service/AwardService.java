package com.myc.scholarship.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.myc.scholarship.entity.Award;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Juci123
 * @since 2018-12-01
 */
public interface AwardService extends IService<Award> {

    Page<Award> pageWithSearch(Page<Award> plusPage, Wrapper<Award> formToEntityWrapperWithSearch);
}
