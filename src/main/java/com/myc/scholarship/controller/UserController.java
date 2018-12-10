package com.myc.scholarship.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.myc.scholarship.entity.User;
import com.myc.scholarship.mapper.UserMapper;
import com.myc.scholarship.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Juci123
 * @since 2018-11-29
 */
@RestController
@RequestMapping("/user")
@Api(value = "管理员测试模块", description =  "测试模块")
public class UserController implements Serializable {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "登录验证",notes = "登录验证")
    @PostMapping(value = "login/{num}/{pwd}")
    public Boolean login(@PathVariable("num")  String num,@PathVariable("pwd") String pwd){
        Boolean success = false;
        List<User> users = userService.selectList(new EntityWrapper<User>().eq("num",num).eq("pwd",pwd).eq("isDeleted",false));
        if(users!=null && users.size()>0){
            success = true;
        }
        return true;
    }
}

