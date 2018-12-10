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

}
