package com.myc.scholarship.service.impl;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.myc.scholarship.entity.User;
import com.myc.scholarship.mapper.UserMapper;
import com.myc.scholarship.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Juci123
 * @since 2018-11-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    /**
    * @description  通过账号获取用户信息
    * @author 马勇超
    * @date 2019/1/14 14:07
    * @param
    * @return
    */
    @Override
    public User selectWithNum(String num) {
        return baseMapper.selectWithNum(num);
    }
}
