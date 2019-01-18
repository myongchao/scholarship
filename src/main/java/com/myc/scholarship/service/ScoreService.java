package com.myc.scholarship.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.myc.scholarship.entity.Score;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Juci123
 * @since 2019-01-10
 */
public interface ScoreService extends IService<Score> {

    Page<Score> selectWithSubject(Page<Score> plusPage, Wrapper<Score> formToEntityWrapperWithSearch);
}
