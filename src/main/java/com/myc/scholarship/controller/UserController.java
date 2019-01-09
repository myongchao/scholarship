package com.myc.scholarship.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.myc.scholarship.entity.User;
import com.myc.scholarship.entity.token.TokenData;
import com.myc.scholarship.entity.token.UserInfoToken;
import com.myc.scholarship.entity.token.ReturnToken;
import com.myc.scholarship.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private UserService userService;
    protected static String REDIRECT = "redirect:";

    @ApiOperation(value = "登录验证",notes = "登录验证")
    @PostMapping(value = "/login")
    @ResponseBody
    public UserInfoToken login(@RequestParam("num")  String num, @RequestParam("pwd") String pwd){
        Boolean success = false;
        List<User> users = userService.selectList(new EntityWrapper<User>().eq("num",num).eq("pwd",pwd).eq("isDeleted",false));
        if(users!=null && users.size()>0){
            success = true;
        }
        UserInfoToken userInfoToken = new UserInfoToken();
        if(success){
            Map<String,String> list = new HashMap<>();
            userInfoToken.setCode(20000);
            list.put("token","admin");
            userInfoToken.setData(list);
        }
        return userInfoToken;
    }

    @ApiOperation(value = "获取token")
    @GetMapping(value = "/info")
    @ResponseBody
    public ReturnToken info(@RequestParam("token")String user){
        ReturnToken returnToken = new ReturnToken();
        TokenData tokenData = new TokenData();
        List<String> list = new ArrayList<>();
        returnToken.setCode(20000);
        list.add(user);
        tokenData.setRoles(list);
        tokenData.setName(user);
        tokenData.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        returnToken.setData(tokenData);
        return returnToken;
    }

    @ApiOperation(value = "登出")
    @PostMapping(value = "/logout")
    public UserInfoToken logOut(@RequestParam("token")String user){
//        Boolean success = false;
//        if (user != null){
//            success = true;
//        }
//        return REDIRECT + "/user/login";
        UserInfoToken userInfoToken = new UserInfoToken();
        Map<String,String> map = new HashMap<>();
        // userInfoToken.setCode();
        map.put("token","");
        userInfoToken.setData(map);
        return userInfoToken;
    }
}

